package com.tsystems.javaschool.tasks.calculator;

public class ExpressionIterator {
    private int index;
    private String inputStr;
    private int numberCount = 0;
    private int signCount = 0;
    private int dotCount = 0;

    public ExpressionIterator(String inputStr) {
        this.index = 0;
        this.inputStr = inputStr;
    }

    public boolean hasNext() {
        return index < inputStr.length() && inputStr.length() != 0 && inputStr != null;
    }

    public String next() {
        StringBuilder value = new StringBuilder();
        char token = inputStr.charAt(index);
        if (Character.toString(token).matches("\"\\\\(|\\\\)|\\\\+|-|\\\\*|/\"") && index == 0)
            throw new IllegalArgumentException();
        while (Character.isDigit(token) || token == '.') {
            value.append(token);
            index++;
            if (index == inputStr.length())
                return value.toString();
            token = inputStr.charAt(index);
            if (token == '.') {
                dotCount++;
            }
            if (token == ',' || dotCount >= 2 || (index > 0 && inputStr.charAt(index - 1) == '('
                    && Character.toString(token).matches("\"\\\\(|\\\\)|\\\\+|-|\\\\*|/\""))) {
                throw new IllegalArgumentException();
            }
        }

        if (value.length() != 0) {
            numberCount++;
            return value.toString();
        }
        index++;
        if (token != ')' && token != '(') {
            signCount++;
            if (signCount != numberCount)
                throw new IllegalArgumentException();
        }
        return Character.toString(token);
    }
}
