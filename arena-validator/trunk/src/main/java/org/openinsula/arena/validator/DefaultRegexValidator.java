package org.openinsula.arena.validator;


public class DefaultRegexValidator implements Validator {
    private String pattern;
    
    public DefaultRegexValidator() {
        pattern = ".*";
    }
    
    public DefaultRegexValidator(String pattern) {
        this.pattern = pattern;
    }

    public boolean validate(String value) {
        if (value != null && pattern != null && value.matches(pattern)) {
            return true;
        }
        return false;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
