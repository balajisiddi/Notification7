package com.sectorseven.model.Enum;

/**
 * @author Ramesh naidu
 *
 */
public enum MaritalStatus {

    Single("Single"), Married("Married"), Divorced("Divorced");

    private String id;

    private MaritalStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
