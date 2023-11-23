package com.rkostiuk.task.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public interface ValidUser {

    @NotEmpty
    String getName();

    @NotEmpty
    String getSurname();

    @NotNull
    @Past
    LocalDate getBirthDate();

    @NotEmpty
    String getLogin();

    @NotEmpty
    String getPassword();
}
