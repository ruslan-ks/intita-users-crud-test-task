package com.rkostiuk.task.validation;

import com.rkostiuk.task.entity.User;
import com.rkostiuk.task.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Optional;

@Component
public class UserValidator extends CustomValidator<ValidUser> {
    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(ValidUser.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var user = (ValidUser) target;
        Optional<User> found = userService.findUserByLogin(user.getLogin());
        if (found.isPresent()) {
            rejectLoginAlreadyInUse(errors);
        }
    }

    private static void rejectLoginAlreadyInUse(Errors errors) {
        errors.rejectValue("login", "Unique", "Already in use");
    }
}
