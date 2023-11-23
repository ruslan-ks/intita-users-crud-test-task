package com.rkostiuk.task.dto.response;

import com.rkostiuk.task.entity.User;

import java.time.LocalDate;

public record UserResponse(Long id, String name, String surname, LocalDate birthDate, String login, String password,
                           String address) {
    public UserResponse(User user) {
        this(user.getId(), user.getName(), user.getSurname(), user.getBirthDate(), user.getLogin(), user.getPassword(),
                user.getAddress());
    }
}
