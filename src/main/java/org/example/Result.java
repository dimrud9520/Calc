package org.example;

class Result {

    private final InputParser action;
    private int arabicResult;
    private String romanResult;

    public Result(InputParser action) {
        this.action = action;
    }

    public String getResultCalculation() throws CalculationException {
        calculation(action.getValue1().getArabic(), action.getValue2().getArabic());

        if (InputType.ARABIC.equals(action.getInputTypeValue())) {
            return String.valueOf(arabicResult);
        } else {

            if (arabicResult <= 0) {
                throw new CalculationException("В римской системе нет отрицательных чисел");
            }

            transformArabicToRoman();
            return romanResult;
        }
    }

    private void transformArabicToRoman() {
        romanResult = "";

        while (arabicResult >= 90) {
            romanResult += "XC";
            arabicResult -= 90;
        }
        while (arabicResult >= 50) {
            romanResult += "L";
            arabicResult -= 50;
        }
        while (arabicResult >= 40) {
            romanResult += "XL";
            arabicResult -= 40;
        }
        while (arabicResult >= 10) {
            romanResult += "X";
            arabicResult -= 10;
        }
        while (arabicResult >= 9) {
            romanResult += "IX";
            arabicResult -= 9;
        }
        while (arabicResult >= 5) {
            romanResult += "V";
            arabicResult -= 5;
        }
        while (arabicResult >= 4) {
            romanResult += "IV";
            arabicResult -= 4;
        }
        while (arabicResult >= 1) {
            romanResult += "I";
            arabicResult -= 1;
        }
    }

    /**
     * Метод действия над двумя переменными
     */
    private void calculation(int value1, int value2) {
        ActionMath actionMath = action.getMathAction();

        if (ActionMath.PLUS.equals(actionMath)) {
            arabicResult = value1 + value2;
        }
        if (ActionMath.MINUS.equals(actionMath)) {
            arabicResult = value1 - value2;
        }
        if (ActionMath.MULTI.equals(actionMath)) {
            arabicResult = value1 * value2;
        }
        if (ActionMath.DEV.equals(actionMath)) {
            arabicResult = value1 / value2;
        }
    }
}