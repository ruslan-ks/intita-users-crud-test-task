package com.rkostiuk.task.exception;

import com.rkostiuk.task.entity.User;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class UserPatchValidationException extends CustomValidationException {
    public UserPatchValidationException(Set<ConstraintViolation<User>> violations) {
        super(violations);
    }
}
