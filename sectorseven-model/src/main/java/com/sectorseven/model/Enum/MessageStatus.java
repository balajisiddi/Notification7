package com.sectorseven.model.Enum;

/**
 * @author Ramesh Naidu
 *
 */
public enum MessageStatus {

    PROCESSING("PROCESSING"), SEND("SEND"), FAILED("FAILED");

    private String id;

    private MessageStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
