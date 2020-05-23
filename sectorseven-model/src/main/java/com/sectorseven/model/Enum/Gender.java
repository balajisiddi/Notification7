package com.sectorseven.model.Enum;

/**
 * @author Ramesh naidu
 *
 */
public enum Gender {

    Male("Male"), Female("Female");

    private String id;

    private Gender(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

}
