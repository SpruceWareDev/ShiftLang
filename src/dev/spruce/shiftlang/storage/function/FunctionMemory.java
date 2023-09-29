package dev.spruce.shiftlang.storage.function;

import java.util.HashMap;
import java.util.Optional;

public class FunctionMemory {
    private static FunctionMemory instance;

    private final HashMap<String, Function> functionHashMap;

    public FunctionMemory() {
        this.functionHashMap = new HashMap<>();
    }

    public void register(String identifier, Function variable) {
        this.functionHashMap.put(identifier, variable);
    }

    public Optional<Function> getFunction(String identifier) {
        Function var;
        if ((var = functionHashMap.get(identifier)) != null)
            return Optional.of(var);
        return Optional.empty();
    }

    public static FunctionMemory getInstance() {
        if (instance == null) {
            instance = new FunctionMemory();
        }
        return instance;
    }
}
