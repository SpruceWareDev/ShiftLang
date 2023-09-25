package dev.spruce.shiftlang.storage.variable;

import java.util.HashMap;
import java.util.Optional;

public class VariableMemory {

    private static VariableMemory instance;

    private final HashMap<String, Variable> variableHashMap;

    public VariableMemory() {
        this.variableHashMap = new HashMap<>();
    }

    public void register(String identifier, Variable variable) {
        this.variableHashMap.put(identifier, variable);
    }

    public Optional<Variable> getVariable(String identifier) {
        Variable var;
        if ((var = variableHashMap.get(identifier)) != null)
            return Optional.of(var);
        return Optional.empty();
    }

    public static VariableMemory getInstance() {
        if (instance == null) {
            instance = new VariableMemory();
        }
        return instance;
    }
}
