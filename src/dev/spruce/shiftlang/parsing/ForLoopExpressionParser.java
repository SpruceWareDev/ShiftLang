package dev.spruce.shiftlang.parsing;

import dev.spruce.shiftlang.Interpreter;
import dev.spruce.shiftlang.storage.variable.DataType;
import dev.spruce.shiftlang.storage.variable.Variable;
import dev.spruce.shiftlang.storage.variable.VariableMemory;
import dev.spruce.shiftlang.util.ForLoopData;

import java.util.Optional;
import java.util.Stack;

public class ForLoopExpressionParser {

    public static ForLoopData parse(String expression) {
        char[] chars = expression.toCharArray();
        Stack<Integer> values = new Stack<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ')
                continue;

            if (Character.isLetterOrDigit(chars[i])) {
                StringBuilder builder = new StringBuilder();
                boolean isNumber = false;
                while (i < chars.length && Character.isLetterOrDigit(chars[i])) {
                    if (Character.isDigit(chars[i])) isNumber = true;
                    builder.append(chars[i]);
                    i++;
                }
                i--;

                if (isNumber) {
                    values.push(Integer.parseInt(builder.toString()));
                    continue;
                }

                Optional<Variable> variable = VariableMemory.getInstance().getVariable(builder.toString());
                if (variable.isEmpty()) throw new RuntimeException("Variable '" + builder + "' is not defined!");
                if (!variable.get().getType().equals(DataType.INT)) throw new RuntimeException("Variable is not of type int!");
                int varValue = (int) variable.get().getValue();
                values.push(varValue);
            }
        }

        if (values.size() != 2) throw new RuntimeException("For loop expression must have 2 values. 'from,to'");
        int to = values.pop();
        int from = values.pop();
        return new ForLoopData(from, to);
    }
}
