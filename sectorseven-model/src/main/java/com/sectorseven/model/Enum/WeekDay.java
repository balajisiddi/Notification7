package com.sectorseven.model.Enum;


/**
 * @author RameshNaidu
 *
 */
public enum WeekDay {

    Monday("Monday"), Tuesday("Tuesday"), Wednesday("Wednesday"), Thursday("Thursday"), Friday("Friday"), Saturday("Saturday"), Sunday("Sunday");

    private String id;

    private WeekDay(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
