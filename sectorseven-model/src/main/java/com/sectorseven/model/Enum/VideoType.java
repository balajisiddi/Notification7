package com.sectorseven.model.Enum;

/**
 * @author Ramesh naidu
 *
 */
public enum VideoType {

    Private("Private"), Youtube("Youtube");

    private String id;

    private VideoType(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

}
