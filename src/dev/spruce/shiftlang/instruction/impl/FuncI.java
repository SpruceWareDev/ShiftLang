package dev.spruce.shiftlang.instruction.impl;

import dev.spruce.shiftlang.Lexer;
import dev.spruce.shiftlang.instruction.Instruction;
import dev.spruce.shiftlang.instruction.InstructionData;
import dev.spruce.shiftlang.storage.function.Function;
import dev.spruce.shiftlang.storage.function.FunctionMemory;
import dev.spruce.shiftlang.storage.variable.DataType;
import dev.spruce.shiftlang.util.TypeUtil;

import java.util.ArrayList;
import java.util.List;

public class FuncI extends Instruction {

    public FuncI() {
        super("func");
    }

    @Override
    public void handleCommand(InstructionData data) {
        String[] typeData = data.getCommandLogic().split(" ", 2);
        if (typeData.length < 2) throw new RuntimeException("Type is needed for a function definition!");
        String typeString = typeData[0];
        DataType returnType = TypeUtil.parseType(typeString);
        if (returnType == null) throw new RuntimeException("Not a valid return type for function definition!");

        String functionDefinition = typeData[1];
        String[] functionData = functionDefinition.split("->", 2);
        if (functionData.length < 2) throw new RuntimeException("Functions need a code body using '->'");

        // Parse function stuff :3
        char[] chars = functionData[0].toCharArray();
        StringBuilder functionIdentifier = new StringBuilder();
        for (char c : chars) {
            if (c == ' ')
                continue;
            if (c == '(')
                break;
            if (Character.isLetter(c)) {
                functionIdentifier.append(c);
            }
        }
        if (functionIdentifier.isEmpty()) throw new RuntimeException("Function have to have an identifier!");
        List<InstructionData> functionInstructions = new ArrayList<>();
        String[] instructions = functionData[1].split(",");
        for (String instruction : instructions) {
            functionInstructions.add(Lexer.parseInstruction(instruction));
        }
        FunctionMemory.getInstance().register(functionIdentifier.toString(), new Function(functionInstructions, returnType));
    }
}
