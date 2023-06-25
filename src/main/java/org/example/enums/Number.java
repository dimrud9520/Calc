package org.example.enums;

public enum Number {
    I(1, -1),
    II(2, -2),
    III(3, -3),
    IV(4, -4),
    V(5, -5),
    VI(6, -6),
    VII(7, -7),
    VIII(8, -8),
    IX(9, -9),
    X(10, -10);

    private final int arab;
    private final int minusArab;


    Number(int arab, int minusArab) {
        this.arab = arab;
        this.minusArab = minusArab;

    }

    public Integer getArab() {
        return arab;
    }

    public Integer getMinusArab() {
        return minusArab;
    }
}
