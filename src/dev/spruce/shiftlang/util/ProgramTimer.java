package dev.spruce.shiftlang.util;

public class ProgramTimer {

    private final long start;

    public ProgramTimer() {
        this.start = System.currentTimeMillis();
    }

    public float getRuntime() {
        return System.currentTimeMillis() - start;
    }
}
