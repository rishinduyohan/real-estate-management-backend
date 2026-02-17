package com.pvt.ecom.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    CUSTOMER,
    OWNER,
    AGENT,
    ADMIN;

    @JsonCreator
    public static Role fromValue(String value) {
        return Role.valueOf(value.toUpperCase());
    }
}
