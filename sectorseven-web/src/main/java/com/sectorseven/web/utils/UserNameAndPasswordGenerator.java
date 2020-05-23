package com.sectorseven.web.utils;

import java.security.SecureRandom;

public class UserNameAndPasswordGenerator {

	private static SecureRandom random = new SecureRandom();

   
    
    public static String generatePassword(int len) {
    	 /** different dictionaries used */
    	String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String ALPHA = "abcdefghijklmnopqrstuvwxyz";
        String NUMERIC = "0123456789";
        String SPECIAL_CHARS = "!@#$%^&*_=+-/";
       
        String result = "";
        String dic =  ALPHA_CAPS + ALPHA + SPECIAL_CHARS+NUMERIC;
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;
        }
    
    
	
}
