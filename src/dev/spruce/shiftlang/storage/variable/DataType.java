package dev.spruce.shiftlang.storage.variable;

public enum DataType {
    STRING(String.class),
    INT(Integer.class),
    FLOAT(Float.class),
    BOOLEAN(Boolean.class);

    final Class<?> castType;

    private DataType(Class<?> castType) {
        this.castType = castType;
    }
}
