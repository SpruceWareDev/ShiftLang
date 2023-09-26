package dev.spruce.shiftlang;

import dev.spruce.shiftlang.instruction.Instruction;
import dev.spruce.shiftlang.instruction.InstructionData;
import dev.spruce.shiftlang.instruction.InstructionSet;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    private final List<InstructionData> instructions;
    private int instructionPoint = -1;

    public Interpreter(List<InstructionData> instructions) {
        this.instructions = instructions;
    }

    public void execute() {
        advance();
        while (instructionPoint < instructions.size()) {
            InstructionData instructionData = instructions.get(instructionPoint);
            handleInstruction(instructionData);
            advance();
        }
    }

    public static void handleInstruction(InstructionData instructionData) {
        Instruction currentInstruction = InstructionSet.getInstance().getInstruction(instructionData.getCommandWord());
        if (currentInstruction == null)
            throw new RuntimeException("Unknown instruction keyword '" + instructionData.getCommandWord() + "'!");
        currentInstruction.handleCommand(instructionData);
    }

    private void advance() {
        instructionPoint++;
    }

    public void jump(int position) {
        this.instructionPoint = position - 1;
    }

    public int getCurrentLine() {
        return instructionPoint + 1;
    }
}
