package org.example.Components;

import org.example.Memory.Memory;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Registers { //singleton, only one instance
    private short[] registers = new short[8];
    private short instructionPointer = Integer.valueOf("fff0").shortValue();
    private short[] flags = new short[3];
    private short stackBase = 0;
    private short stackSize = 128;
    private short stackPointer = 0;

    public Registers(short stackSize){
        Arrays.fill(registers, (short) 0);
        Arrays.fill(flags, (short) 0);
        instructionPointer = Integer.valueOf("fff0", 16).shortValue();
        stackPointer = 0;
        this.stackSize = stackSize;
        stackBase = 0;
    }

    public void reset() {
        Arrays.fill(registers, 0, 7, (short) 0);
        Arrays.fill(flags, 0, 2, (short) 0);
        instructionPointer = Short.parseShort("fff0", 16);
        stackBase = 0;
        stackSize = 0;
        stackPointer = 0;
    }

    public short loadFromRegister(int index) {
        if (index < 8 && index >= 0) {
            return registers[index];
        }
        throw new IllegalArgumentException("Register: " + index + "does not exist!");
    }

    public void storeToRegister(int index, short data) {
        if (index < 8 && index >= 0) {
            registers[index] = data;
        }
        throw new IllegalArgumentException("Invalid register: " + index);
    }

    public short getInstructionPointer() {
        return instructionPointer;
    }

    public short[] getFlags() {
        return flags;
    }

    public short getStackPointer() {
        return stackPointer;
    }

    public void setStackPointer(short stackPointer) {
        if(this.stackPointer < Integer.valueOf("fff0", 16).shortValue()){
            throw new IllegalArgumentException("Illegal value for stack pointer.");
        }
        this.stackPointer = stackPointer;
    }

    public void setInstructionPointer(short instructionPointer) {
        this.instructionPointer = instructionPointer;
    }
    public void setZFlag(short []flags){
        this.flags[0] = flags[0];
    }

    public void setFlags(short[] flags) {
        this.flags = flags;
    }
}
