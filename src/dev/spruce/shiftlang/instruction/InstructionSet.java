package dev.spruce.shiftlang.instruction;

import dev.spruce.shiftlang.instruction.impl.CommentI;
import dev.spruce.shiftlang.instruction.impl.ForI;
import dev.spruce.shiftlang.instruction.impl.PrintI;
import dev.spruce.shiftlang.instruction.impl.VarI;

import java.util.ArrayList;
import java.util.List;

public class InstructionSet {

    public static InstructionSet instance;

    private final List<Instruction> instructionSet = new ArrayList<>();

    private InstructionSet() {
        this.instructionSet.add(new PrintI());
        this.instructionSet.add(new VarI());
        this.instructionSet.add(new CommentI());
        this.instructionSet.add(new ForI());
    }

    public Instruction getInstruction(String commandWord) {
        for (Instruction instruction : instructionSet) {
            if (instruction.getCommandWord().equals(commandWord)) {
                return instruction;
            }
        }
        return null;
    }

    public static InstructionSet getInstance() {
        if (instance == null) {
            instance = new InstructionSet();
        }
        return instance;
    }
}
