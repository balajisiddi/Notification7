package com.sectorseven.model.Enum;

/**
 * @author Ramesh Naidu
 */

public enum Salutation {

	 Mr("Mr"), Mrs("Mrs"), Ms("Ms"), Dr("Dr");
	 
    private String id;

    private Salutation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
