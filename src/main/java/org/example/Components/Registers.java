package org.example.Components;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Registers {
    private short[] registers = new short[8];
    private short instructionPointer;
    private short[] flags = new short[3];
    private short stackBase;
    private short stackSize;
    private short stackPointer;

    public void reset(){
        Arrays.fill(registers, 0, 7, (short) 0);
        Arrays.fill(flags, 0, 2, (short) 0);
        instructionPointer = Short.parseShort("fff0", 16);
        stackBase = 0;
        stackSize = 0;
        stackPointer = 0;
    }
}
