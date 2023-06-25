package org.example;

import org.example.enums.ActionMath;
import org.example.enums.Number;
import org.example.exception.MathActionException;
import org.example.exception.VariablesException;

import java.util.Scanner;

/**
 * Класс  получения выражения от пользователя
 */
public final class UserAction {
    private final Scanner sc;
    private String mathAction;
    private int value1;
    private int value2;
    private int whod;
    private char znak;
    private boolean rimAction = false;

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public boolean isRimAction() {
        return rimAction;
    }

    public UserAction() {
        value1 = 0;
        value2 = 0;

        System.out.println("Введите действие римскими либо арабскими цифрами");

        sc = new Scanner(System.in);
        mathAction = sc.nextLine();

        try {
            checkZnack();
            if (isArabNumber()) {
                arabNumber();
            } else {
                rimNumber();
                rimAction = true;
            }

        } catch (MathActionException | VariablesException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Метод получения знака из введенного выражения
     *
     * @return знак
     */
    public char getZnak() {
        for (ActionMath count : ActionMath.values()) {
            if (count.getMathAction() == (mathAction.charAt(getWhod()))) {
                znak = count.getMathAction();
            }
        }
        return znak;
    }

    /**
     * Метод получения переменных, если пользователь ввел выражения арабскими числами
     */
    private void arabNumber() throws VariablesException {
        value1 = Integer.parseInt(mathAction.substring(0, getWhod()));
        value2 = Integer.parseInt(mathAction.substring(getWhod() + 1, mathAction.length()));

        if (value1 > 10 || value1 < 1 || value2 > 10 || value2 < 1) {
            throw new VariablesException("Числа могут быть только от 1 до 10");
        }
    }

    /**
     * Метод определия ялвляются ли переменные арабскими цифрами
     */
    private boolean isArabNumber() {
        try {
            String str = mathAction.substring(0, 1);

            Integer.parseInt(str);

            return true;
        } catch (NumberFormatException e) {

            return false;
        }
    }

    /**
     * Метод получения переменных, если пользователь ввел выражения римскими числами
     */
    private void rimNumber() throws VariablesException {
        for (Number count : Number.values()) {
            if (count.name().equalsIgnoreCase(mathAction.substring(0, getWhod()))) {
                value1 = count.getArab();
            }
            if (count.name().equalsIgnoreCase(mathAction.substring(getWhod() + 1, mathAction.length()))) {
                value2 = count.getArab();
            }
        }
        if (value1 == 0) throw new VariablesException("Некорректно введено первое число");
        if (value2 == 0) throw new VariablesException("Некорректно введено второе число");
    }

    /**
     * Метод определения точки входа знака
     * return позицию входа знака
     */
    private Integer getWhod() {
        for (int i = 0; i < mathAction.length(); i++) {

            for (ActionMath count : ActionMath.values()) {
                if (count.getMathAction() == (mathAction.charAt(i))) {
                    whod = i;
                }
            }
        }
        return whod;
    }

    /**
     * Метод проверки корректности математического знака
     */
    private void checkZnack() throws MathActionException {
        int ok = 0;

        for (ActionMath count : ActionMath.values()) {
            for (int i = 0; i < mathAction.length(); i++) {

                if (mathAction.charAt(i) == count.getMathAction()) {
                    ok += 1;
                }
            }
        }

        if (ok > 1 || ok == 0) {
            throw new MathActionException("Ошибка математического знака");
        }
    }
}
