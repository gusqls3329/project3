package com.team5.projrental.common;

public enum Flag {
    LIST(0), ONE(1);

    private int value;

    Flag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
