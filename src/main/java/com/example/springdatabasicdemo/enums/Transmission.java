package com.example.springdatabasicdemo.enums;

public enum Transmission {
    MANUAL(0),
    AUTOMATIC(1);

    private final int value;

    Transmission(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
