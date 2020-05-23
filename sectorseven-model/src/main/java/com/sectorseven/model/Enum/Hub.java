package com.sectorseven.model.Enum;

/**
 * @author Ramesh naidu
 *
 */
public enum Hub {

    Yes("Yes"), No("No");

    private String id;

    private Hub(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

}
