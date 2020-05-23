package com.sectorseven.model.Enum;

/**
 * @author Ramesh naidu
 *
 */
public enum Colors {

	Color1("#da9833"),Color2("#3c70a4"),Color3("#64b2cd");

    private String id;

    private Colors(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
