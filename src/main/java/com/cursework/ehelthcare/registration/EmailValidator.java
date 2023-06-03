package com.cursework.ehelthcare.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * The type Email validator.
 */
@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        // TODO: Regex to validate email
        return true;
    }
}
