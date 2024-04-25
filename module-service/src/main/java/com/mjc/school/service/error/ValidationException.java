package com.mjc.school.service.error;

import java.util.function.Supplier;

public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }
}
