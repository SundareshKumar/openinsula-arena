package org.openinsula.arena.gwt.application.client.history.temp.rebind;

import java.io.PrintWriter;

import org.openinsula.arena.gwt.application.client.history.temp.HistoryItem;
import org.openinsula.arena.gwt.application.client.history.temp.View;
import org.openinsula.arena.gwt.application.client.history.temp.ViewDisposer;
import org.openinsula.arena.gwt.application.client.history.temp.ViewProvider;
import org.openinsula.arena.gwt.rebind.AbstractGenerator;
import org.openinsula.arena.gwt.rebind.EnhancedSourceWriter;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class HistoryItemGenerator extends AbstractGenerator {

	private EnhancedSourceWriter srcWriter;
	
	private View viewAnnotation;
	
	private JMethod viewProviderMethod;
	
	private JMethod viewDisposerMethod;
	
	@Override
	protected String doGenerate() throws Exception {
		final String packageName = getRequestedClass().getPackage().getName();
		final String className = getRequestedClass().getSimpleSourceName() + "_HistoryItem";

		final ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(packageName,
				className);
		composerFactory.addImport(Widget.class.getName());
		composerFactory.addImport(HistoryItem.class.getName());
		composerFactory.setSuperclass(HistoryItem.class.getName());

		final PrintWriter printWriter = context.tryCreate(logger, packageName, className);
		if (printWriter == null) {
			return null;
		}

		final SourceWriter sw = composerFactory.createSourceWriter(context, printWriter);
		if (sw == null) {
			return null;
		}

		srcWriter = new EnhancedSourceWriter(sw);
		
		viewAnnotation = getRequestedClass().getAnnotation(View.class);
		resolveViewProvider(getRequestedClass());
		resolveViewDisposer(getRequestedClass());
		
		srcWriter.indent();
		writeBody();
		srcWriter.endBlock();
		srcWriter.commit(logger);
		
		return String.format("%s.%s", packageName, className);
	}

	private void resolveViewProvider(final JClassType requestedClass) {
		viewProviderMethod = null;
		
		for (JMethod method : requestedClass.getMethods()) {
			ViewProvider annotation = method.getAnnotation(ViewProvider.class);
			
			if (annotation != null) {
				if (viewProviderMethod != null) {
					throw new IllegalStateException("Multiple occurences of @ViewProvider");
				}
				viewProviderMethod = method;
			}
		}
	}
	
	private void resolveViewDisposer(final JClassType requestedClass) {
		viewDisposerMethod = null;
		
		for (JMethod method : requestedClass.getMethods()) {
			ViewDisposer annotation = method.getAnnotation(ViewDisposer.class);
			
			if (annotation != null) {
				if (viewDisposerMethod != null) {
					throw new IllegalStateException("Multiple occurences of @ViewDisposer");
				}
				viewDisposerMethod = method;
			}
		}
	}

	private void writeBody() throws Exception {
		writeGetHistoryToken();
		writeCreateWidget();
		
		writeIsSingletonIfNecessary();
		writeDeleteOnHideIfNecessary();
	}
	
	/**
	 * <pre>
	 * protected Widget createWidget(String historyToken) {
	 * }
	 * </pre>
	 * @throws UnableToCompleteException 
	 */
	private void writeCreateWidget() throws UnableToCompleteException {
		srcWriter.println("protected Widget createWidget(String historyToken) ");
		srcWriter.beginBlock();
		
		if (viewProviderMethod == null) {
			srcWriter.printf("return new %s();", getRequestedClass().getSimpleSourceName());
		} else {
			StringBuilder parameters = new StringBuilder();
			
			JParameter[] pArray = viewProviderMethod.getParameters();
			
			if (pArray.length > 0) {
				parameters.append(resolveParameterValue(pArray[0]));
				
				for (int i = 1; i < pArray.length; i++) {
					parameters.append(',').append(resolveParameterValue(pArray[i]));
				}
			}
			
			srcWriter.printf("return %s(%s);", viewProviderMethod.getName(), parameters.toString());
		}
		
		srcWriter.endBlock();
	}
	
	private String resolveParameterValue(final JParameter parameter) {
		String pName = parameter.getName();
		JType pType = parameter.getType();
		
		if (pType.isPrimitive() != null) {
			JPrimitiveType primitive = pType.isPrimitive();
			
			if (primitive == JPrimitiveType.BOOLEAN) {
				return "false";
			}
			
			return "0";
			
		} else {
			if ("historyToken".equals(pName) && pType == getTypeOracle().findType("java.lang.String")) {
				return "historyToken";
			}
			return "null";
		}
		
	}

	private void writeIsSingletonIfNecessary() {
		if (viewAnnotation != null && !viewAnnotation.singleton()) {
			srcWriter.println("public boolean isSingleton() ");
			srcWriter.beginBlock().println("return false;");
			srcWriter.endBlock();
		}
	}
	
	private void writeDeleteOnHideIfNecessary() {
		if (viewAnnotation != null && !viewAnnotation.deleteOnHide()) {
			srcWriter.println("public boolean deleteOnHide() ");
			srcWriter.beginBlock().println("return false;");
			srcWriter.endBlock();
		}
	}

	/**
	 * <pre>
	 * public String getHistoryToken() {
	 *   return "token";
	 * }
	 * </pre>
	 */
	private void writeGetHistoryToken() throws UnableToCompleteException {
		srcWriter.println("public String getHistoryToken() ");
		srcWriter.beginBlock();
		
		String token = viewAnnotation == null ? 
				getRequestedClass().getSimpleSourceName().toLowerCase() : viewAnnotation.value();
				
		srcWriter.printf("return \"%s\";", token).endBlock();
	}

}
