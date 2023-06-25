package org.example;

/**
 * Класс  получения выражения от пользователя
 */
class InputParser {

    private final String input;
    private ActionMath mathAction;
    private int entryPointMathAction; // индекс входа матетматического знака в строку
    private Number value1;
    private Number value2;
    private InputType inputTypeValue;

    public ActionMath getMathAction() {
        return mathAction;
    }

    public void setMathAction(ActionMath mathAction) {
        this.mathAction = mathAction;
    }

    public void setEntryPointMathAction(int entryPointMathAction) {
        this.entryPointMathAction = entryPointMathAction;
    }

    public Number getValue1() {
        return value1;
    }

    public void setValue1(Number value1) {
        this.value1 = value1;
    }

    public Number getValue2() {
        return value2;
    }

    public void setValue2(Number value2) {
        this.value2 = value2;
    }

    public InputType getInputTypeValue() {
        return inputTypeValue;
    }

    public void setInputTypeValue(InputType inputTypeValue) {
        this.inputTypeValue = inputTypeValue;
    }

    public InputParser(String input) throws MathActionException, VariablesException {
        this.input = input;

        inputValidate();

        //получение знака из строки
        setMathAction(checkAndGetMathSign());
        //получения типа чисел(арабские или римские)
        setInputTypeValue(checkAndGetInputType());
        //получение и присвоение значений
        setAndCheckValues();
    }

    private void inputValidate() throws VariablesException {
        if (input.length() > 9) {
            throw new VariablesException("формат математической операции не удовлетворяет заданию");
        }
        if (input.length() < 3) {
            throw new VariablesException("строка не является математической операцией");
        }
    }

    private void checkValues() throws VariablesException {
        if (value1 == null) {
            throw new VariablesException("Некорректно введен первый операнд");
        }
        if (value2 == null) {
            throw new VariablesException("Некорректно введен второй операнд");
        }
    }

    private void setAndCheckValues() throws VariablesException {
        setValue1(getValue(0, entryPointMathAction));
        setValue2(getValue(entryPointMathAction + 1, input.length()));

        checkValues();
    }

    private Number getValue(int beginIndex, int endIndex) {
        Number result = null;

        for (Number count : Number.values()) {
            if (inputTypeValue.equals(InputType.ARABIC)) {
                int value = Integer.parseInt(input.substring(beginIndex, endIndex));

                if (value == count.getArabic()) {
                    result = count;
                }
            } else {

                if (input.substring(beginIndex, endIndex).equals(count.name())) {
                    result = count;
                }
            }
        }
        return result;
    }

    private InputType checkAndGetInputType() throws VariablesException {
        InputType inputType1 = getInputType(0, entryPointMathAction);
        InputType inputType2 = getInputType(entryPointMathAction + 1, input.length());
        if (!inputType1.equals(inputType2)) {
            throw new VariablesException("используются одновременно разные системы счисления");
        } else {
            return inputType1;
        }
    }

    private InputType getInputType(int beginIndex, int endIndex) {
        try {
            Integer.parseInt(this.input.substring(beginIndex, endIndex));

        } catch (NumberFormatException e) {
            return InputType.ROMAN;
        }
        return InputType.ARABIC;
    }

    private ActionMath checkAndGetMathSign() throws MathActionException {
        ActionMath result = null;
        checkMathSing(input);

        for (ActionMath actionMath : ActionMath.values()) {
            if (input.charAt(entryPointMathAction) == actionMath.getMathAction()) {
                result = actionMath;
            }
        }

        return result;
    }

    private void checkMathSing(String input) throws MathActionException {
        int ok = 0;

        for (ActionMath action : ActionMath.values()) {
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == action.getMathAction()) {
                    setEntryPointMathAction(i);
                    ok++;
                }
            }
        }
        if (ok == 0) {
            throw new MathActionException("Отсутствует математический знак");
        } else if (ok > 1) {
            throw new MathActionException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
    }
}
