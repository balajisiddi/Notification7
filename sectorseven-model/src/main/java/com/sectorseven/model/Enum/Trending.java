package com.sectorseven.model.Enum;


/**
 * @author RameshNaidu
 *
 */
public enum Trending {

    IsTrending("IsTrending"), IsPrevious("IsPrevious");

    private String id;

    private Trending(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

}
