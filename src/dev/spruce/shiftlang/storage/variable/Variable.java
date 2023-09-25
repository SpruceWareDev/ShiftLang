package dev.spruce.shiftlang.storage.variable;

public class Variable {

    private DataType type;
    private Object value;

    public Variable(DataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Class<?> getCastedValue() {
        return (Class<?>) type.castType.cast(value);
    }

    public DataType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
