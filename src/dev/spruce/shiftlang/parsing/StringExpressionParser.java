package dev.spruce.shiftlang.parsing;

import dev.spruce.shiftlang.storage.variable.Variable;
import dev.spruce.shiftlang.storage.variable.VariableMemory;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExpressionParser {

    /*
    public static String parseExpression(String inputData) {
        StringBuilder builder = new StringBuilder();

        char[] charData = inputData.toCharArray();
        int position = 0;
        boolean buildingString = false,
                buildingIdentifier = false,
                expectingExpression = false;
        StringBuilder tempIdentifierBuilder = new StringBuilder();

        while (position < charData.length) {

            if (charData[position] == '"') {
                buildingString = !buildingString;
                position++;
                continue;
            }

            if (expectingExpression && charData[position] != ' ') {
                buildingIdentifier = true;
            } else if (expectingExpression) {
                expectingExpression = false;
            }

            if (buildingString) {
                builder.append(charData[position]);
            } else if (buildingIdentifier && charData[position] != ' ') {
                tempIdentifierBuilder.append(charData[position]);
            } else {

                if (!tempIdentifierBuilder.isEmpty()) {
                    Optional<Variable> variable = VariableMemory.getInstance().getVariable(tempIdentifierBuilder.toString());
                    if (variable.isEmpty())
                        throw new RuntimeException("Variable '" + tempIdentifierBuilder + "' has not been assigned!");
                    builder.append(variable.get().getValue().toString());
                }
                tempIdentifierBuilder = new StringBuilder();
                buildingIdentifier = false;

                switch (charData[position]) {
                    case ' ' -> {position++;continue;}
                    case '+' -> expectingExpression = true;
                    default -> throw new RuntimeException("Incorrect string expressions used!");
                }
            }
            position++;
        }

        if (buildingString)
            throw new RuntimeException("Failed to parse string expression!");
        if (expectingExpression)
            throw new RuntimeException("Expected another expression but got none!");

        return builder.toString();
    }

     */

    public static String parse(String input) {
        StringBuilder builder = new StringBuilder();

        String[] splitExpressions = input.split("\\+");
        for (String expression : splitExpressions) {
            builder.append(parseSingleExpression(expression));
        }

        return builder.toString();
    }

    private static String parseSingleExpression(String expression) {
        StringBuilder builder = new StringBuilder();
        char[] chars = expression.toCharArray();
        boolean building = false, isVariable = true;
        for (char c : chars) {
            if (c == ' ' && !building)
                continue;

            if (c == '"') {
                building = !building;
                isVariable = false;
                continue;
            }

            builder.append(c);
        }

        if (building) throw new RuntimeException("Failed to parse string expression!");

        if (isVariable) {
            Optional<Variable> variable = VariableMemory.getInstance().getVariable(builder.toString());
            if (variable.isEmpty()) throw new RuntimeException("Variable '" + builder + "' is not defined!");
            return variable.get().getValue().toString();
        }

        return builder.toString();
    }
}
