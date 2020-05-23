package com.sectorseven.service.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Real
 *
 */
public final class DateUtlity {

    private DateUtlity() {
        // not called
    }

    public static Date formatDate(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.getLocalizedMessage();
        }
        return date;
    }
}
