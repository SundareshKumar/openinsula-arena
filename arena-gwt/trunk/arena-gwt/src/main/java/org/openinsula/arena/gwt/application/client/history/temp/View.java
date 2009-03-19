package org.openinsula.arena.gwt.application.client.history.temp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface View {

	public boolean singleton() default true;
	
	public boolean deleteOnHide() default true;
	
	/**
	 * Token
	 */
	public String value();
	
}
