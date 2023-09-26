package dev.spruce.shiftlang.instruction.impl;

import dev.spruce.shiftlang.Interpreter;
import dev.spruce.shiftlang.Lexer;
import dev.spruce.shiftlang.instruction.Instruction;
import dev.spruce.shiftlang.instruction.InstructionData;
import dev.spruce.shiftlang.parsing.ForLoopExpressionParser;
import dev.spruce.shiftlang.storage.variable.VariableMemory;
import dev.spruce.shiftlang.util.ForLoopData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForI extends Instruction {

    public ForI() {
        super("for");
    }

    @Override
    public void handleCommand(InstructionData data) {
        String logic = data.getCommandLogic();
        String[] splitExpression = logic.split("->", 2);
        if (splitExpression.length < 2) throw new RuntimeException("You must have a loop expression followed by '->'!");

        ForLoopData forLoopData = ForLoopExpressionParser.parse(splitExpression[0]);

        String[] instructions = splitExpression[1].split(",");
        List<InstructionData> instructionData = new ArrayList<>();
        for (String s : instructions) {
            instructionData.add(Lexer.parseInstruction(s));
        }

        for (int i = forLoopData.getFrom(); i < forLoopData.getTo(); i++) {
            for (InstructionData instruction : instructionData) {
                Interpreter.handleInstruction(instruction);
            }
        }
    }
}
