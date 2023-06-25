package org.example;

import org.example.enums.Number;

public final class Result {

    private final UserAction action;
    private int arabResult;
    private String rimResult;

    public Result(UserAction action) {
        this.action = action;

        if (action.isRimAction()) {
            getResult();

            if (arabResult < 0) {

                System.out.println(minusRimResult());
            } else if (arabResult == 0) {

                System.out.println(arabResult);
            } else {

                System.out.println(transformRim());
            }

        } else {

            getResult();
            System.out.println(arabResult);
        }
    }

    /**
     * Метод преобразования ответа в рискую цифру
     *
     * @return строку с римской цифрой
     */
    private String transformRim() {
        int input = arabResult;

        while (input >= 90) {
            rimResult += "XC";
            input -= 90;
        }
        while (input >= 50) {
            rimResult += "L";
            input -= 50;
        }
        while (input >= 40) {
            rimResult += "XL";
            input -= 40;
        }
        while (input >= 10) {
            rimResult += "X";
            input -= 10;
        }
        while (input >= 9) {
            rimResult += "IX";
            input -= 9;
        }
        while (input >= 5) {
            rimResult += "V";
            input -= 5;
        }
        while (input >= 4) {
            rimResult += "IV";
            input -= 4;
        }
        while (input >= 1) {
            rimResult += "I";
            input -= 1;
        }
        return rimResult;
    }

    /**
     * Метод действия над двумя переменными
     */
    private void getResult() {
        if (action.getZnak() == '+') {
            arabResult = action.getValue1() + action.getValue2();
        }
        if (action.getZnak() == '-') {
            arabResult = action.getValue1() - action.getValue2();
        }
        if (action.getZnak() == '*') {
            arabResult = action.getValue1() * action.getValue2();
        }
        if (action.getZnak() == '/') {
            arabResult = action.getValue1() / action.getValue2();
        }
    }

    /**
     * Метод получения отрицательного значения римских цифр
     */
    private String minusRimResult() {
        for (Number count : Number.values()) {
            if (arabResult == count.getMinusArab()) {
                rimResult = "-" + count.name();
            }
        }

        return rimResult;
    }
}