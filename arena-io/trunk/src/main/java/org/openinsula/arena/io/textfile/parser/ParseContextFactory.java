package org.openinsula.arena.io.textfile.parser;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.line.LineFactory;

public class ParseContextFactory {
	protected static final Log logger = LogFactory.getLog(ParseContextFactory.class);

	protected static Map<Class<?>, Map<Class<? extends LineFactory>, Method>> parseMapCache = new HashMap<Class<?>, Map<Class<? extends LineFactory>, Method>>();

	protected static String charsetName = "ISO-8859-1";

	public static ParseContext newParseContext(InputStream in, Object handler) {
		Map<Class<? extends LineFactory>, Method> parseMap = parseMapCache.get(handler.getClass());
		if (parseMap == null) {
			parseMap = buildParseMap(handler.getClass());
			parseMapCache.put(handler.getClass(), parseMap);
		}

		return new InputStreamParseContext(in, Charset.forName(charsetName), parseMap, handler);
	}

	protected static Map<Class<? extends LineFactory>, Method> buildParseMap(Class<?> klazz) {
		Map<Class<? extends LineFactory>, Method> map = new HashMap<Class<? extends LineFactory>, Method>();

		List<Class<?>> classList = new ArrayList<Class<?>>();
		classList.addAll(Arrays.asList(klazz.getInterfaces()));
		classList.add(klazz);

		for (Class<?> c : classList) {
			Method[] methods = c.getMethods();

			for (Method method : methods) {
				LineParser annotation = method.getAnnotation(LineParser.class);
				Class<?>[] parameterTypes = method.getParameterTypes();

				if (annotation != null) {
					if (parameterTypes.length == 1 && parameterTypes[0] == Line.class) {
						Class<? extends LineFactory> lineFactoryClass = annotation.value();
						if (lineFactoryClass != null) {
							if (map.containsKey(lineFactoryClass)) {
								Method overridenMethod = map.get(lineFactoryClass);
								if (logger.isWarnEnabled()) {
									logger.warn("Overriding LineFactory definition of method "
											+ overridenMethod.getName() + " of class "
											+ overridenMethod.getDeclaringClass().getName() + " with method "
											+ method.getName() + " of class " + method.getDeclaringClass().getName());
								}
							}
							map.put(lineFactoryClass, method);
						}
					}
					else {
						logger.warn("Found method with @LineParser, but no parameter of class Line.");
					}
				}
			}
		}

		return map;
	}

}
