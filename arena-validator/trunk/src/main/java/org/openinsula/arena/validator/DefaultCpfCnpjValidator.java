package org.openinsula.arena.validator;


public class DefaultCpfCnpjValidator implements Validator {

    public boolean validate(String value) {
        value = StringUtils.getDigitsOnly(value);
        Validator validator = (value.length()>11) ? new DefaultCnpjValidator() : new DefaultCpfValidator();
        return validator.validate(value);
    }
}
