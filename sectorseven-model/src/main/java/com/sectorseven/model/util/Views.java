package com.sectorseven.model.util;

/**
 * Set of market interfaces to control json serialization via {@link com.fasterxml.jackson.annotation.JsonView JsonView}
 */
public interface Views {

    /**
     * Essential only fields that can be exposed via the public interface
     */
    public interface PublicLight {
    }

    /**
     * Fields that can be exposed via the public interface.
     */
    public interface Public extends PublicLight {
    }

    /**
     * Fields that can be exposed via the DATA (CMS) interface.
     */
    public interface Data extends Public {
    }

}
