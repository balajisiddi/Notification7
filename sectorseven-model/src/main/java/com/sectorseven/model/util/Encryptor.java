package com.sectorseven.model.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Basic utility for encrypting and checking passwords
 */
public final class Encryptor {

    private static final int TWELEVE = 12;

    /**
     * Hash a {@link java.lang.String}
     * 
     * @param value the value to hash
     * @return the hashed value
     */
    public static String encrypt(final String value) {
        return BCrypt.hashpw(value, BCrypt.gensalt(TWELEVE));
    }

    /**
     * Check that a plaintext password value a previously hashed one
     * 
     * @param value the plaintext value to verify
     * @param hashed the previously-hashed value
     * @return true if the values match, false otherwise
     */
    public static boolean check(final String value, final String hashed) {
        return BCrypt.checkpw(value, hashed);
    }
}
