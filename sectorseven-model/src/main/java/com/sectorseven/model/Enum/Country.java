package com.sectorseven.model.Enum;

/**
 * @author Ramesh naidu
 *
 */
public enum Country {

    India("India"),Abroad("Abroad"),IND("IND"), USA("USA"),UK("UK"),AUS("AUS"),CAN("CAN");

    private String id;

    private Country(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
