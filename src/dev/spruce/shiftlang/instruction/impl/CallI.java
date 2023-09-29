package dev.spruce.shiftlang.instruction.impl;

import dev.spruce.shiftlang.instruction.Instruction;
import dev.spruce.shiftlang.instruction.InstructionData;
import dev.spruce.shiftlang.storage.function.Function;
import dev.spruce.shiftlang.storage.function.FunctionMemory;

import java.util.Optional;

public class CallI extends Instruction {

    public CallI() {
        super("call");
    }

    @Override
    public void handleCommand(InstructionData data) {
        String functionIdentifier = data.getCommandLogic().trim();
        Optional<Function> function = FunctionMemory.getInstance().getFunction(functionIdentifier);
        if (function.isEmpty()) throw new RuntimeException("Function '" + functionIdentifier + "' is not defined!");
        function.get().evaluate();
    }
}
