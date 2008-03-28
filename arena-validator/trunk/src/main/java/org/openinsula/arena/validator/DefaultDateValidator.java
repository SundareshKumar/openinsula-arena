package org.openinsula.arena.validator;

import java.util.Calendar;



public class DefaultDateValidator implements Validator {

    public boolean validate(String value) {
    	try {
    		if (value == null || "".equals(value) || !value.matches("[0-9]{2,2}/[0-9]{2,2}/[0-9]{4,4}")) {
    			return false;
    		}
    		
    		String[] date = value.split("/");
    		int day = Integer.parseInt(date[0]);
    		int month = Integer.parseInt(date[1]) -1;
    		int year = Integer.parseInt(date[2]);
    		
    		Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE, day);
    		calendar.set(Calendar.MONTH, month);
    		calendar.set(Calendar.YEAR, year);
    		
    		return calendar.get(Calendar.DATE) == day && 
    				calendar.get(Calendar.MONTH) == month &&
    				calendar.get(Calendar.YEAR) == year;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}
