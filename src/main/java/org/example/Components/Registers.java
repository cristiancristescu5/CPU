package org.example.Components;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Registers { //singleton, only one instance
    private static short[] registers = new short[8];
    private static short instructionPointer;
    private static short[] flags = new short[3];
    private static short stackBase;
    private static short stackSize;
    private static short stackPointer;
    static {
        Arrays.fill(registers, (short) 0);
        Arrays.fill(flags, (short) 0);
        instructionPointer = Integer.valueOf("fff0", 16).shortValue();
        stackPointer = 0;
        stackSize = 0;
        stackBase = 0;
    }
    public static void reset(){
        Arrays.fill(registers, 0, 7, (short) 0);
        Arrays.fill(flags, 0, 2, (short) 0);
        instructionPointer = Short.parseShort("fff0", 16);
        stackBase = 0;
        stackSize = 0;
        stackPointer = 0;
    }
    public static short loadFromRegister(int index){
        if(index < 8 && index >= 0){
            return registers[index];
        }
        throw new IllegalArgumentException("Register: " + index + "does not exist!");
    }
    public static void storeToRegister(int index, short data){
        if(index < 8 && index >=0){
            registers[index] = data;
        }
        throw new IllegalArgumentException("Invalid register: " + index);
    }

    public static short getInstructionPointer() {
        return instructionPointer;
    }

    public static short[] getFlags() {
        return flags;
    }

    public static short getStackPointer() {
        return stackPointer;
    }

}
