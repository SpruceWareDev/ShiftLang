package dev.spruce.shiftlang.storage.function;

import dev.spruce.shiftlang.Interpreter;
import dev.spruce.shiftlang.instruction.InstructionData;
import dev.spruce.shiftlang.storage.variable.DataType;
import dev.spruce.shiftlang.storage.variable.Variable;
import dev.spruce.shiftlang.storage.variable.VariableMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Function {

    private final List<InstructionData> instructions;
    private final DataType returnType;

    public Function(List<InstructionData> instructions, DataType returnType) {
        this.instructions = instructions;
        this.returnType = returnType;
    }

    public Object evaluate() {
        Optional<Variable> returnValue = Optional.empty();
        for (InstructionData instruction : instructions) {
            if (instruction.getCommandWord().equals("return")) {
                returnValue = VariableMemory.getInstance().getVariable(instruction.getCommandLogic());
            }
            Interpreter.handleInstruction(instruction);
        }
        if (returnValue.isEmpty() && !returnType.equals(DataType.VOID)) throw new RuntimeException("Cannot find return variable!");
        if (returnType.equals(DataType.VOID)) return null;
        return returnValue.get().getValue();
    }

    public List<InstructionData> getInstructions() {
        return instructions;
    }

    public DataType getReturnType() {
        return returnType;
    }
}
