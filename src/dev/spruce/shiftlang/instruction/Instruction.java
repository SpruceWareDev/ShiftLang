package dev.spruce.shiftlang.instruction;

public abstract class Instruction {

    private String commandWord;

    public Instruction(String commandWord) {
        this.commandWord = commandWord;
    }

    public abstract void handleCommand(InstructionData data);

    public String getCommandWord() {
        return commandWord;
    }
}
