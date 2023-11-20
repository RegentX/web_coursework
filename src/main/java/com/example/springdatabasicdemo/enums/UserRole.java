package com.example.springdatabasicdemo.enums;

public enum UserRole {
    USER(0),
    ADMIN(1);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
