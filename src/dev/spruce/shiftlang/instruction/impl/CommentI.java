package dev.spruce.shiftlang.instruction.impl;

import dev.spruce.shiftlang.instruction.Instruction;
import dev.spruce.shiftlang.instruction.InstructionData;

public class CommentI extends Instruction {

    public CommentI() {
        super("#");
    }

    @Override
    public void handleCommand(InstructionData data) {

    }
}
