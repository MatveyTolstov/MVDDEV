package com.example.mvd_dev.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Roles{
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_EMPLOYEE;

    @JsonCreator
    public static Roles fromString(String value) {
        return Roles.valueOf(value.toUpperCase());
    }
}
