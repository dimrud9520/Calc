package org.example;

enum ActionMath {

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
