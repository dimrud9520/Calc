package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите математическое выражение из двух опердандов и одного оператора римскими либо арабскими цифрами");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            System.out.println(calc(input));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String calc(String input) throws VariablesException, MathActionException, CalculationException {
        InputParser inputParser = new InputParser(input);
        Result result = new Result(inputParser);

        return result.getResultCalculation();
    }

}