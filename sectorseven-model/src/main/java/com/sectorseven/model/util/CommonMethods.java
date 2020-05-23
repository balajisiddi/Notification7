package com.sectorseven.model.util;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Real
 *
 */

public class CommonMethods {

    private static final int NINES = 999999;
    private static final int TENS = 100000;
    private static final String HIPEN = "-";
    private static SecureRandom random = new SecureRandom();
    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static int randomNumberGenration() {

        SecureRandom random = new SecureRandom();
        Integer secureNumber = random.nextInt((NINES - TENS) + 1);
        return secureNumber;
    }

    public static String ddmmyyyy(String yyyymmdd) {
        String formattedDate = null;
        if (StringUtils.isNotBlank(yyyymmdd)) {
            String[] str = yyyymmdd.split(HIPEN);
            String year = str[0];
            String month = str[1];
            String date = str[2];
            formattedDate = date + HIPEN + month + HIPEN + year;
        }
        return formattedDate;

    }

    public static String yyyymmdd(String ddmmyyyy) {
        String formattedDate = null;
        if (StringUtils.isNotBlank(ddmmyyyy)) {
            String[] str = ddmmyyyy.split(HIPEN);
            String year = str[2];
            String month = str[1];
            String date = str[0];
            formattedDate = year + HIPEN + month + HIPEN + date;
        }
        return formattedDate;
    }

    public static Date stringToDate(String yyymmdd) {
        Date givenDate = null;
        if (StringUtils.isNotBlank(yyymmdd)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                givenDate = sdf.parse(yyymmdd);
            } catch (ParseException e) {
                e.getMessage();
            }
            sdf = null;
        }
        return givenDate;
    }

    public static Date stringddToyyDate(String payDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date givenDate = null;
        try {
            givenDate = sdf.parse(yyyymmdd(payDate));
        } catch (ParseException e) {
            e.getLocalizedMessage();
        }
        return givenDate;
    }

    public static String datetoStringddmmyy(Date yyddmm) {
        String formatedDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        formatedDate = sdf.format(yyddmm);
        formatedDate = ddmmyyyy(formatedDate);
        return formatedDate;

    }
    
    
    /* (non-Javadoc)
     *	This method returns digits alphanumerical password 
     */
    	public static String generatePassword(int len) {
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
    	
    	public static String decodePassword(String password) {
    		String decodedPassword = passwordEncoder.encode(password);
    		return decodedPassword;
    	}

    	
    
    	
}
