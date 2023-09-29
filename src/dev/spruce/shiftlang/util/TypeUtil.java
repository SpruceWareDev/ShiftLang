package dev.spruce.shiftlang.util;

import dev.spruce.shiftlang.storage.variable.DataType;

public class TypeUtil {

    public static DataType parseType(String input) {
        switch (input) {
            case "int" -> {return DataType.INT;}
            case "float" -> {return DataType.FLOAT;}
            case "boolean" -> {return DataType.BOOLEAN;}
            case "string" -> {return DataType.STRING;}
            case "void" -> {return DataType.VOID;}
            default -> {return null;}
        }
    }
}
