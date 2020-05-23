
package com.sectorseven.web.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Returned when user is not authorised to view/edit data.
 *
 */
/**
 * @author RameshNaidu
 *
 */
@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class UnauthorizedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
        super("Access Denied.");
    }
}
