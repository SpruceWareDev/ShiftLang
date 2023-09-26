package dev.spruce.shiftlang.parsing;

import dev.spruce.shiftlang.storage.variable.DataType;
import dev.spruce.shiftlang.storage.variable.Variable;
import dev.spruce.shiftlang.storage.variable.VariableMemory;

import javax.management.RuntimeMBeanException;
import java.io.CharConversionException;
import java.util.Optional;
import java.util.Stack;

public class BooleanExpressionParser {

    public static boolean parse(String expression) {
        char[] chars = expression.toCharArray();
        Stack<Boolean> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ')
                continue;

            if (Character.isLetter(chars[i])) {
                StringBuilder word = new StringBuilder();
                while (i < chars.length && Character.isLetter(chars[i])) {
                    word.append(chars[i]);
                    i++;
                }
                i--;
                switch (word.toString()) {
                    case "true" -> values.push(true);
                    case "false" -> values.push(false);
                    default -> {
                        Optional<Variable> variable = VariableMemory.getInstance().getVariable(word.toString());
                        if (variable.isEmpty()) throw new RuntimeException("Variable '" + word + "' is not defined!");
                        if (!variable.get().getType().equals(DataType.BOOLEAN)) throw new RuntimeException("Variable is not of type boolean!");
                        boolean varValue = (boolean) variable.get().getValue();
                        values.push(varValue);
                    }
                }
            } else if (isOperator(chars[i])) {
                operators.push(chars[i]);
            }
        }

        while (!operators.isEmpty()) {
            char currentOperator = operators.pop();
            if (currentOperator == '!') {
                values.push(applyOperator('!', values.pop(), false));
                continue;
            }
            values.push(applyOperator(currentOperator, values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean isOperator(char c) {
        switch (c) {
            case '&', '|', '!' -> {return true;}
            default -> {return false;}
        }
    }

    private static boolean applyOperator(char operator, boolean operand1, boolean operand2) {
        switch (operator) {
            case '&' -> {return operand1 && operand2;}
            case '|' -> {return operand1 || operand2;}
            case '!' -> {return !operand1;}
            default -> throw new RuntimeException("Unknown operator '" + operator + "'!");
        }
    }

}
