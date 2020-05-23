package com.sectorseven.model.Enum;


/**
 * @author RameshNaidu
 *
 */
public enum YesNo {

    Yes("Yes"), No("No");

    private String id;

    private YesNo(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

}
