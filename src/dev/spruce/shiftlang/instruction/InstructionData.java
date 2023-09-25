package dev.spruce.shiftlang.instruction;

public class InstructionData {

    private String commandWord;
    private String commandLogic;
    private String fullCommand;

    public InstructionData(String commandWord, String commandLogic, String fullCommand) {
        this.commandWord = commandWord;
        this.commandLogic = commandLogic;
        this.fullCommand = fullCommand;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public void setCommandWord(String commandWord) {
        this.commandWord = commandWord;
    }

    public String getCommandLogic() {
        return commandLogic;
    }

    public void setCommandLogic(String commandLogic) {
        this.commandLogic = commandLogic;
    }

    public String getFullCommand() {
        return fullCommand;
    }

    public void setFullCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
}
