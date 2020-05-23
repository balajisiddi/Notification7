package com.sectorseven.model.Enum;


/**
 * @author RameshNaidu
 *
 */
public enum AdminAcceptance {

	Submitted("Submitted"), Accepted("Accepted"),Rejected("Rejected"),ReqNotRaised("ReqNotRaised");

    private String id;

    private AdminAcceptance(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
