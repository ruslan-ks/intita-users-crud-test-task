package com.rkostiuk.task.dto.request;

import java.time.LocalDate;
import java.util.Objects;

public class PatchUserRequest {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String password;
    private String address;

    public PatchUserRequest(String name, String surname, LocalDate birthDate, String password, String address) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.password = password;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatchUserRequest that = (PatchUserRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(birthDate, that.birthDate) && Objects.equals(password, that.password) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate, password, address);
    }

    @Override
    public String toString() {
        return "PatchUserRequest{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
