package dev.spruce.shiftlang.instruction.impl;

import dev.spruce.shiftlang.instruction.Instruction;
import dev.spruce.shiftlang.instruction.InstructionData;
import dev.spruce.shiftlang.parsing.NumberExpressionParser;
import dev.spruce.shiftlang.parsing.StringExpressionParser;
import dev.spruce.shiftlang.storage.variable.DataType;
import dev.spruce.shiftlang.storage.variable.Variable;
import dev.spruce.shiftlang.storage.variable.VariableMemory;

public class VarI extends Instruction {

    public VarI() {
        super("var");
    }

    @Override
    public void handleCommand(InstructionData data) {
        String logic = data.getCommandLogic();
        String[] splitData = logic.split(" ");

        if (splitData.length < 1) {
            throw new RuntimeException("A variable needs a data type!");
        } else if (splitData.length < 2) {
            throw new RuntimeException("A variable needs and identifier!");
        }

        String type = splitData[0], identifier = splitData[1];
        int offset = type.length() + identifier.length() + 2;
        String assignmentExpression = logic.substring(offset);

        if (!assignmentExpression.startsWith("=")) {
            throw new RuntimeException("Assignment of variable must start with '='!");
        }

        switch (type) {
            case "string" -> {
                String parsedExpression = StringExpressionParser.parse(assignmentExpression.substring(1));
                VariableMemory.getInstance().register(identifier, new Variable(DataType.STRING, parsedExpression));
            }
            case "int" -> {
                int parsedExpression = (int) NumberExpressionParser.parse(assignmentExpression.substring(1));
                VariableMemory.getInstance().register(identifier, new Variable(DataType.INT, parsedExpression));
            }
            case "float" -> {
                float parsedExpression = NumberExpressionParser.parse(assignmentExpression.substring(1));
                VariableMemory.getInstance().register(identifier, new Variable(DataType.FLOAT, parsedExpression));
            }
            default -> throw new RuntimeException("Unknown data type for variable: '" + type + "'!");
        }
    }
}
