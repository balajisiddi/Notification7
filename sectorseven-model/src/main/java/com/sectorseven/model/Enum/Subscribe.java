package com.sectorseven.model.Enum;


/**
 * @author RameshNaidu
 *
 */
public enum Subscribe {

	Subscribed("Subscribed"), UnSubscribed("UnSubscribed");

    private String id;

    private Subscribe(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
