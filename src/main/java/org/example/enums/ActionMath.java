package org.example.enums;

public enum ActionMath {

    PLUS('+'),
    MINUS('-'),
    DEV('/'),
    MULTI('*');

    private final char value;

    ActionMath(char value) {
        this.value = value;
    }

    public char getMathAction() {
        return value;
    }
}
