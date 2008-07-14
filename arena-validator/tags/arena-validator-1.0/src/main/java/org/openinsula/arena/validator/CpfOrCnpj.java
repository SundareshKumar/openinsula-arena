package org.openinsula.arena.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

@ValidatorClass(CpfOrCnpjValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CpfOrCnpj {
	String message() default "{validator.cpfOrCnpj}";
}
