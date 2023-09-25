package dev.spruce.shiftlang.instruction.impl;

import dev.spruce.shiftlang.instruction.Instruction;
import dev.spruce.shiftlang.instruction.InstructionData;
import dev.spruce.shiftlang.parsing.StringExpressionParser;

public class PrintI extends Instruction {

    public PrintI() {
        super("print");
    }

    @Override
    public void handleCommand(InstructionData data) {
        String logic = data.getCommandLogic();
        String parsedString = StringExpressionParser.parse(logic);
        System.out.println(parsedString);
    }
}
