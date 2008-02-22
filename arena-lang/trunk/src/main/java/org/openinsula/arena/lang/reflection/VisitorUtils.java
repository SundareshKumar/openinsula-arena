package org.openinsula.arena.lang.reflection;

import java.lang.reflect.Method;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public abstract class VisitorUtils {

	public static Object accept(Object visited, Object visitor) {
		return accept(visited, visitor, "visit");
	}
	
	public static Object accept(Object visited, Object visitor, String visitMethodName) {
		Assert.notNull(visited, "Visited object required!");
		Assert.notNull(visitor, "Visitor object required!");
		Assert.hasText(visitMethodName, "Visitor 'visit' methodName missing!");
		
		Method method = ReflectionUtils.findMethod(
				visitor.getClass(),
				visitMethodName,
				new Class<?>[] { visited.getClass() });
		
		if (method != null) {
			return ReflectionUtils.invokeMethod(
					method, 
					visitor, 
					new Object[] { visited });
		}
		
		throw new IllegalArgumentException("Visitor method [" + visitMethodName + "] not found!");
	}
	
}
