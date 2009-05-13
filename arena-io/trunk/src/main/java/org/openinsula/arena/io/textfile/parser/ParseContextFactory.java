/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena I/O.
 *
 *  Arena I/O is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena I/O is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena I/O.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.io.textfile.parser;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.line.LineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseContextFactory {
	protected static final Logger logger = LoggerFactory.getLogger(ParseContextFactory.class);

	protected static final Map<Class<?>, Map<Class<? extends LineFactory>, Method>> parseMapCache = new HashMap<Class<?>, Map<Class<? extends LineFactory>, Method>>();

	protected static final String charsetName = "ISO-8859-1";

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
