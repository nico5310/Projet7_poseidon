package com.nnk.springboot.security;

import lombok.extern.log4j.Log4j2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PasswordValidator is methods of validating password with parameters.
 */
@Log4j2
public class PasswordValidator {
    // digit 8<password<50 + lowercase char + uppercase char + punctuation + symbol
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,50}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    /**
     * Validate password with parameters
     * @param password paramater for validating
     * @return true boolean for valid password / false boolean for invalid password
     */
    public static boolean isValid(final String password) {
        log.info("SUCCESS, password is validate");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
