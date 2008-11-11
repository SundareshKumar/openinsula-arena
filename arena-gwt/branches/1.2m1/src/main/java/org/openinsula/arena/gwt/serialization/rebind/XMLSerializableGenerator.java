package org.openinsula.arena.gwt.serialization.rebind;

import java.io.PrintWriter;

import org.openinsula.arena.gwt.rebind.AbstractGenerator;
import org.openinsula.arena.gwt.rebind.EnhancedSourceWriter;
import org.openinsula.arena.gwt.rebind.GeneratorUtils;
import org.openinsula.arena.gwt.serialization.client.xml.XMLSerializable;
import org.openinsula.arena.gwt.serialization.client.xml.XMLSerializableImpl;
import org.openinsula.arena.gwt.serialization.client.xml.XMLSerializerImpl;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.Text;
import com.google.gwt.xml.client.XMLParser;

public class XMLSerializableGenerator extends AbstractGenerator {
	private JClassType serializeInterface;

	private EnhancedSourceWriter srcWriter;

	private String className;

	protected String doGenerate() throws Exception {
		serializeInterface = getTypeOracle().findType(XMLSerializable.class.getName());
		assert (serializeInterface != null);

		String packageName = getRequestedClass().getPackage().getName();
		className = getRequestedClass().getSimpleSourceName() + "_TypeSerializer";

		PrintWriter printWriter = context.tryCreate(logger, packageName, className);
		if (printWriter == null) {
			return null;
		}

		final String superclassName = "XMLSerializerImpl";

		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(packageName, className);
		composerFactory.addImport(Element.class.getName());
		composerFactory.addImport(Document.class.getName());
		composerFactory.addImport(Node.class.getName());
		composerFactory.addImport(Text.class.getName());
		composerFactory.addImport(NodeList.class.getName());
		composerFactory.addImport(XMLParser.class.getName());
		composerFactory.addImport(XMLSerializableImpl.class.getName());
		composerFactory.addImport(XMLSerializerImpl.class.getName());
		composerFactory.setSuperclass(superclassName);

		SourceWriter sw = composerFactory.createSourceWriter(context, printWriter);
		if (sw == null) {
			return null;
		}
		
		srcWriter = new EnhancedSourceWriter(sw);

		JClassType[] subTypes = serializeInterface.getSubtypes();

		if (subTypes.length > 0) {
			String[] tagNames = new String[subTypes.length];
			String[] subTypeGeneratedName = new String[subTypes.length];

			for (int i = 0; i < subTypes.length; i++) {
				tagNames[i] = resolveTagName(subTypes[i]);
				subTypeGeneratedName[i] = subTypes[i].getName() + superclassName;

				srcWriter.printf("public class %s extends %s", subTypeGeneratedName[i], superclassName).beginBlock();
				srcWriter.writeEmptyDefaultConstructor(subTypeGeneratedName[i]);

				writeToXML(subTypes[i]);
				writeFromXML(subTypes[i]);

				srcWriter.endBlock();
			}

			srcWriter.declareDefaultConstructor(className).beginBlock();

			for (int i = 0; i < subTypes.length; ++i) {
				srcWriter.printf("addType(\"%s\", new %s());", tagNames[i], subTypeGeneratedName[i]).newLine();
			}

			srcWriter.endBlock();
		}
		
//		srcWriter.endBlock();
		srcWriter.commit(logger);
		return String.format("%s.%s", packageName, className);
	}

	private String resolveTagName(final JClassType classType) {
		char[] sourceName = classType.getSimpleSourceName().toCharArray();
		sourceName[0] = Character.toLowerCase(sourceName[0]);
		return String.valueOf(sourceName);
	}

	private void writeToXML(final JClassType classType) throws UnableToCompleteException {

		srcWriter.printf("public String toXML(Document document, Element parent, XMLSerializable obj)").beginBlock();

		srcWriter.printf("Element root = document.createElement(\"%s\");", resolveTagName(classType)).newLine();
		srcWriter.printf("Node rootParent = parent == null ? document : parent;").newLine();
		srcWriter.printf("rootParent.appendChild(root);").newLine();
		
		String fullName = classType.getQualifiedSourceName();
		srcWriter.printf("%s objImpl = (%s) obj;", fullName, fullName).newLine();

		GeneratorUtils.doWithMethods(classType, new GeneratorUtils.MethodCallback() {
			public void doWith(final JMethod method) {
				String property = GeneratorUtils.getPropertyName(method);
				String var = String.format("objImpl.%s()", method.getName());
				JType varType = method.getReturnType();
				writeToXMLAttribute(property, var, varType, "root");
			}
		}, GeneratorUtils.isGetter());

		srcWriter.printf("return null;").endBlock();
	}

	private void writeToXMLAttribute(final String tagName, final String varExpression, final JType varType,
			final String elementVarExpression) {
		
		if (varType.isClass() != null && varType.isClass().isAssignableTo(serializeInterface)) {
			srcWriter.printf("%s.this.toXML(document, %s, %s);", className, elementVarExpression, varExpression)
					.newLine();
		}
		else if (varType.isArray() != null) {
			JArrayType array = varType.isArray();
			JType componentType = array.getComponentType();

			srcWriter.beginBlock();
			srcWriter.printf("%s array = %s;", array.getQualifiedSourceName(), varExpression).newLine();
			srcWriter.printf("if (array != null && array.length > 0)").beginBlock();
			srcWriter.printf("Element list = document.createElement(\"%s\");", tagName).newLine();
			srcWriter.printf("%s.appendChild(list);", elementVarExpression).newLine();
			srcWriter.printf("for (%s var : array)", componentType.getQualifiedSourceName()).beginBlock();
			writeToXMLAttribute("item", "var", componentType, "list");
			srcWriter.endBlock().endBlock().endBlock();
			
		}
		else if (GeneratorUtils.isCollection(varType.isClassOrInterface(), getTypeOracle())) {
//			JParameterizedType collection = varType.isParameterized();
		}
		else {					
			srcWriter.printf("writeSimpleAttribute(\"%s\", %s, %s, document);", tagName, varExpression,
					elementVarExpression).newLine();
		}
	}

	private void writeFromXML(final JClassType classType) {
//		String fullName = classType.getQualifiedSourceName();

		srcWriter.printf("public XMLSerializable fromXML(Element element)").beginBlock();
		
		// TODO
		srcWriter.printf("return null;").endBlock();
		
//		srcWriter.printf("%s obj = new %s();", fullName, fullName).newLine();
//		srcWriter.println("NodeList itemList = null;");
//
//		GeneratorUtils.doWithMethods(classType, new GeneratorUtils.MethodCallback() {
//			public void doWith(final JMethod method) {
//				JParameter parameter = method.getParameters()[0];
//
//				String elementName = GeneratorUtils.getPropertyName(method);
//				srcWriter.printf("itemList = element.getElementsByTagName(\"%s\");", elementName).newLine();
//				srcWriter.printf("if (itemList.getLength() > 0)").beginBlock();
//
//				if (parameter.getType().isArray() != null) {
//					// set up loop for array
//					JArrayType array = parameter.getType().isArray();
//					String typeName = array.getQualifiedSourceName();
//					String componentTypeName = array.getComponentType().getQualifiedSourceName();
//
//					srcWriter.printf("%s arrayValue = new %s[itemList.getLength()];", typeName, componentTypeName)
//							.newLine();
//					srcWriter.printf("for (int i = 0; i < arrayValue.length; i++)").beginBlock();
//					srcWriter.println("Element elementValue = (Element) itemList.item(i);");
//
//					// get value from xml
//					writeFromXMLAttribute(method.getName(), array.getComponentType());
//					srcWriter.printf("arrayValue[i] = value;").endBlock();
//					srcWriter.printf(" obj.%s(arrayValue);", method.getName()).newLine();
//				}
//				else {
//					srcWriter.println("Element elementValue = (Element) itemList.item(0);");
//					writeFromXMLAttribute(method.getName(), parameter.getType());
//					srcWriter.printf(" obj.%s(value);", method.getName()).newLine();
//				}
//
//				srcWriter.endBlock();
//			}
//		}, GeneratorUtils.isSetter());
//
//		srcWriter.println("return obj;");
//		srcWriter.endBlock().newLine();
	}

//	private void writeFromXMLAttribute(final String methodName, final JType type) {
//		String fullName = type.getQualifiedSourceName();
//
//		srcWriter.printf("%s value = null;", fullName).newLine();
//
//		if (type.isPrimitive() != null) {
//			JPrimitiveType primitiveType = type.isPrimitive();
//
//			srcWriter.printf("if (elementValue.hasChildNodes())").beginBlock();
//
//			String fromXML = null;
//
//			if (primitiveType == JPrimitiveType.BOOLEAN)
//				fromXML = "Boolean.parseBoolean";
//			else if (primitiveType == JPrimitiveType.BYTE)
//				fromXML = "Byte.parseByte";
//			else if (primitiveType == JPrimitiveType.DOUBLE)
//				fromXML = "Double.parseDouble";
//			else if (primitiveType == JPrimitiveType.FLOAT)
//				fromXML = "Float.parseFloat";
//			else if (primitiveType == JPrimitiveType.INT)
//				fromXML = "Integer.parseInt";
//			else if (primitiveType == JPrimitiveType.LONG)
//				fromXML = "Long.parseLong";
//			else if (primitiveType == JPrimitiveType.SHORT)
//				fromXML = "Short.parseShort";
//			else
//				throw new IllegalStateException("Unexpected type: " + fromXML);
//
//			srcWriter.printf("value = %s(elementValue.getFirstChild().getNodeValue());", fromXML).endBlock();
//		}
//		else {
//			JClassType paramClassType = type.isClass();
//			if (paramClassType.isAssignableTo(serializeInterface)) {
//				srcWriter.printf("value = (%s) %s.this.fromXML(\"%s\", elementValue);", fullName, className,
//						paramClassType.getSimpleSourceName()).newLine();
//			}
//			else {
//				// must be a string?
//				srcWriter.printf("if (elementValue.hasChildNodes())").beginBlock();
//				srcWriter.printf("value = elementValue.getFirstChild().getNodeValue();").endBlock();
//			}
//		}
//	}
}
