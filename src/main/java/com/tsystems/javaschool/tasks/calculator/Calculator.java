package com.tsystems.javaschool.tasks.calculator;

import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

public class Calculator {
    private static HashMap<Character, Integer> priorities = new HashMap() {{
        put('+', 1);
        put('-', 1);
        put('*', 2);
        put('/', 2);
        put('(', 3);
        put(')', 3);
    }};

    Stack<Double> values = new Stack<>();
    Stack<Character> sign = new Stack<>();

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here
        if (statement == null || statement.length() == 0) {
            return null;
        }
        statement = statement.trim();
        try {
            parse(statement);
        } catch (ArithmeticException | IllegalArgumentException e) {
            return null;
        }
        Double result = values.pop();
        if (result != null) {
            DecimalFormat format = new DecimalFormat("#.####");
            return format.format(result);
        } else return null;
    }

    private void parse(String str) {
        int closeBracket = 0;
        int openBracket = 0;
        ExpressionIterator iterator = new ExpressionIterator(str);
        while (iterator.hasNext()) {
            String token = iterator.next();
            if (token.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
                values.push(Double.parseDouble(token));
                continue;
            }
            if (token.equals("(")) {
                sign.push(token.charAt(0));
                openBracket++;
            } else if (token.equals(")")) {
                closeBracket++;
                if (closeBracket > openBracket) {
                    throw new IllegalArgumentException();
                }
                while (!sign.peek().equals('('))
                    solve();
                sign.pop();
            } else {
                if (sign.empty()) {
                    sign.push(token.charAt(0));
                } else {
                    int priority = priorities.get(token.charAt(0));
                    while (priority <= priorities.get(sign.peek()) && !sign.empty() && !sign.peek().equals('(') && !sign.peek().equals(')')) {
                        solve();
                    }
                    sign.push(token.charAt(0));
                }
            }
        }
        if (closeBracket != openBracket) {
            throw new IllegalArgumentException();
        }
        while (!sign.empty()) {
            solve();
        }
    }

    private void solve() {
        try {
            Double value1 = values.pop();
            Double value2 = values.pop();
            Character action = sign.pop();
            switch (action) {
                case '+':
                    value1 += value2;
                    break;
                case '-':
                    value1 = value2 - value1;
                    break;
                case '*':
                    value1 *= value2;
                    break;
                case '/': {
                    if (value1 == 0)
                        throw new ArithmeticException();
                    value1 = value2 / value1;
                    break;
                }
            }
            values.push(value1);
        } catch (EmptyStackException e) {
            throw new IllegalArgumentException();
        }
    }
}
