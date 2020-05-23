package com.sectorseven.model.Enum;

/**
 * @author Ramesh naidu
 *
 */
public enum Months {
    SelectMonth("SelectMonth"), January("January"), February("February"), March("March"), April("April"), May("May"), June("June"), July("July"), August(
            "August"), September("September"), October("October"), November("November"), December("December");

    private String id;

    private Months(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

}
