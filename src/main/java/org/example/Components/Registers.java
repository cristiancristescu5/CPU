package org.example.Components;

public class Registers {
    private short[] registers = new short[8];
    private short instructionPointer;
    private short[] flags = new short[3];
    private short stackBase;
    private short stackSize;
    private short stackPointer;
}
