package com.user.service.domain.enums;

import lombok.Getter;

@Getter
public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    UNSPECIFIED("UNSPECIFIED");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

}
