package com.sectorseven.model.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.util.Views.Public;
import com.sectorseven.model.util.Views.PublicLight;


/**
 * Represents a list of validation error messages
 */
public class ValidationErrorMessages {

    /** List of field errors */
    @JsonProperty(value = "fieldErrors")
    @JsonView(value = { Public.class, PublicLight.class })
    private List<FieldError> fieldErrors = new ArrayList<>();

    /**
     * Adds the error created using the supplied parameters to the list of
     * validation errors.
     *
     * @param field
     *            the field in error
     * @param code
     *            the error code
     * @param message
     *            the error message
     */
    public void addError(String field, String code, String message) {
        FieldError fieldError = new FieldError(field, code, message);
        fieldErrors.add(fieldError);
    }

    /**
     * Returns true if there are errors, false otherwise
     * 
     * @return true if there are errors, false otherwise
     */
    public boolean containsErrors() {
        return !fieldErrors.isEmpty();
    }
}
