package dev.spruce.shiftlang.parsing;

import dev.spruce.shiftlang.storage.variable.DataType;
import dev.spruce.shiftlang.storage.variable.Variable;
import dev.spruce.shiftlang.storage.variable.VariableMemory;

import java.util.Optional;
import java.util.Stack;

public class NumberExpressionParser {
    /*
    public static float parse(String expression) {
        Stack<Float> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--; // Move back one position to account for the loops increment

                values.push(Float.parseFloat(num.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    float result = applyOperator(operators.pop(), values.pop(), values.pop());
                    values.push(result);
                }
                operators.pop(); // Pop the '('
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    float result = applyOperator(operators.pop(), values.pop(), values.pop());
                    values.push(result);
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            float result = applyOperator(operators.pop(), values.pop(), values.pop());
            values.push(result);
        }

        return values.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }

    private static float applyOperator(char operator, float b, float a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

     */

    public static float parse(String expression) {
        Stack<Float> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--; // Move back one position to account for the loop's increment

                values.push(Float.parseFloat(num.toString()));
            } else if (Character.isLetter(c)) {
                StringBuilder varName = new StringBuilder();
                while (i < expression.length() && Character.isLetter(expression.charAt(i))) {
                    varName.append(expression.charAt(i));
                    i++;
                }
                i--; // Move back one position to account for the loop's increment

                float varValue = handleVariable(varName.toString());
                values.push(varValue);
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    float result = applyOperator(operators.pop(), values.pop(), values.pop());
                    values.push(result);
                }
                operators.pop(); // Pop the '('
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    float result = applyOperator(operators.pop(), values.pop(), values.pop());
                    values.push(result);
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            float result = applyOperator(operators.pop(), values.pop(), values.pop());
            values.push(result);
        }

        return values.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }

    private static float applyOperator(char operator, float b, float a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static float handleVariable(String identifier) {
        Optional<Variable> variable = VariableMemory.getInstance().getVariable(identifier);
        if (variable.isEmpty()) throw new RuntimeException("Could not find variable '" + identifier + "'!");
        switch (variable.get().getType()) {
            case INT, FLOAT -> {return (float) variable.get().getValue();}
            default -> throw new RuntimeException("Variable must be of type int or float to be used in a math expression!");
        }
    }
}
