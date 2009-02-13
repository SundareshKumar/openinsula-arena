package org.openinsula.arena.gwt.rebind;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Concept (and javadocs) borrowed from Spring ReflectionUtils class
 * @author Eduardo Rebola
 * 
 */
public abstract class GeneratorUtils {

	private static MethodFilter isGetterCache;

	private static MethodFilter isSetterCache;
	
	public static MethodFilter isGetter() {
		if (isGetterCache == null) {
			isGetterCache = new MethodFilter() {
				public boolean matches(final JMethod method) {
					return method.isPublic()
							&& method.getParameters().length == 0
							&& (method.getName().startsWith("get") || method.getName().startsWith("is"));
				}
			};
		}
		return isGetterCache;
	}

	public static MethodFilter isSetter() {
		if (isSetterCache == null) {
			isSetterCache = new MethodFilter() {
				public boolean matches(final JMethod method) {
					return method.isPublic() 
							&& method.getParameters().length == 1 
							&& method.getName().startsWith("set");
				}
			};
		}
		return isSetterCache;
	}

	public static String getPropertyName(final JMethod method) {
		String methodName = method.getName();
		char[] property = null;

		if (methodName.startsWith("set") || methodName.startsWith("get")) {
			property = methodName.substring(3).toCharArray();
		}
		else if (methodName.startsWith("is")) {
			property = methodName.substring(2).toCharArray();
		}

		property[0] = Character.toLowerCase(property[0]);

		return String.valueOf(property);
	}

	public static boolean isCollection(final JClassType type, final TypeOracle typeOracle) {
		if (type == null) {
			return false;
		}
		JClassType collectionType = typeOracle.findType("java.util.Collection");
		return collectionType.isAssignableFrom(type);
	}
	
	/**
	 * Perform the given callback operation on all matching methods of the given
	 * class and superclasses.
	 * <p>
	 * The same named method occurring on subclass and superclass will appear
	 * twice, unless excluded by a {@link MethodFilter}.
	 * @param targetClass class to start looking at
	 * @param mc the callback to invoke for each method
	 * @see #doWithMethods(Class, MethodCallback, MethodFilter)
	 */
	public static void doWithMethods(final JClassType targetClass, final MethodCallback mc) {
		doWithMethods(targetClass, mc, null);
	}

	/**
	 * Perform the given callback operation on all matching methods of the given
	 * class and superclasses.
	 * <p>
	 * The same named method occurring on subclass and superclass will appear
	 * twice, unless excluded by the specified {@link MethodFilter}.
	 * @param targetClass class to start looking at
	 * @param mc the callback to invoke for each method
	 * @param mf the filter that determines the methods to apply the callback to
	 */
	public static void doWithMethods(JClassType targetClass, final MethodCallback mc, final MethodFilter mf) {

		// Keep backing up the inheritance hierarchy.
		do {
			JMethod[] methods = targetClass.getMethods();
			for (int i = 0; i < methods.length; i++) {
				if (mf != null && !mf.matches(methods[i])) {
					continue;
				}
				mc.doWith(methods[i]);
			}
			targetClass = targetClass.getSuperclass();
		} while (targetClass != null && !"Object".equals(targetClass.getSimpleSourceName()));
	}

	/**
	 * Invoke the given callback on all fields in the target class, going up the
	 * class hierarchy to get all declared fields.
	 * @param targetClass the target class to analyze
	 * @param fc the callback to invoke for each field
	 */
	public static void doWithFields(final JClassType targetClass, final FieldCallback fc)
			throws IllegalArgumentException {
		doWithFields(targetClass, fc, null);
	}

	/**
	 * Invoke the given callback on all fields in the target class, going up the
	 * class hierarchy to get all declared fields.
	 * @param targetClass the target class to analyze
	 * @param fc the callback to invoke for each field
	 * @param ff the filter that determines the fields to apply the callback to
	 */
	public static void doWithFields(JClassType targetClass, final FieldCallback fc, final FieldFilter ff)
			throws IllegalArgumentException {

		// Keep backing up the inheritance hierarchy.
		do {
			// Copy each field declared on this class unless it's static or
			// file.
			JField[] fields = targetClass.getFields();
			for (int i = 0; i < fields.length; i++) {
				// Skip static and final fields.
				if (ff != null && !ff.matches(fields[i])) {
					continue;
				}
				fc.doWith(fields[i]);
			}
			targetClass = targetClass.getSuperclass();
			
		} while (targetClass != null && !"Object".equals(targetClass.getSimpleSourceName()));
	}

	/**
	 * Action to take on each method.
	 */
	public static interface MethodCallback {

		/**
		 * Perform an operation using the given method.
		 * @param method the method to operate on
		 */
		void doWith(JMethod method);
	}

	/**
	 * Callback optionally used to method fields to be operated on by a method
	 * callback.
	 */
	public static interface MethodFilter {

		/**
		 * Determine whether the given method matches.
		 * @param method the method to check
		 */
		boolean matches(JMethod method);
	}

	/**
	 * Callback interface invoked on each field in the hierarchy.
	 */
	public static interface FieldCallback {

		/**
		 * Perform an operation using the given field.
		 * @param field the field to operate on
		 */
		void doWith(JField field);
	}

	/**
	 * Callback optionally used to filter fields to be operated on by a field
	 * callback.
	 */
	public static interface FieldFilter {

		/**
		 * Determine whether the given field matches.
		 * @param field the field to check
		 */
		boolean matches(JField field);
	}

}
