package dev.spruce.shiftlang.instruction;

import dev.spruce.shiftlang.instruction.impl.*;

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
        this.instructionSet.add(new IncludeI());
        this.instructionSet.add(new FuncI());
        this.instructionSet.add(new CallI());
        this.instructionSet.add(new ReturnI());
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
