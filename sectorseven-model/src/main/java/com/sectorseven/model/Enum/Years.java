package com.sectorseven.model.Enum;

/**
 * @author Ramesh Naidu 
 * The different roles an {@link model.security.Administrator} can have
 */
public enum Years {
    
	 First("2020"), Second("2025"), Third("2030");
	 
    private String id;

    private Years(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
	

}
