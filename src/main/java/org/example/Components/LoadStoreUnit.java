package org.example.Components;

import org.example.Memory.Memory;

public class LoadStoreUnit {
    private static final Registers registers = new Registers((short) 128);

    public static void reset() {
        registers.reset();
        Memory.reset();
    }

    public static short loadFromRegister(int index) throws IllegalArgumentException {
        return registers.loadFromRegister(index);
    }

    public static void storeToRegister(int index, short data) {
        registers.storeToRegister(index, data);
    }

    public static short getInstructionPointer() {
        return registers.getInstructionPointer();
    }

    public static short[] getFlags() {
        return registers.getFlags();
    }

    public static short getStackPointer() {
        return registers.getStackPointer();
    }

    public static void loadFromMemoryIntoRegister(int index, short addr) {
        String data = String.valueOf(Memory.loadFromAddress(addr, (short) 1)).substring(12, 15);
        short numData = Integer.valueOf(data).shortValue();
        registers.storeToRegister(index, numData);
    }

    public static void storeToMemoryFromRegister(int index, short addr) {
        Memory.storeToAddress(addr, registers.loadFromRegister(index));
    }

    public static void setStackPointer(short addr) {
        registers.setStackPointer(addr);
    }
    public static short getInstruction(){
        return (short) Memory.loadInstruction(registers.getInstructionPointer(), (short) 4);
    }
    public static void incrementProgramCounter(){
        // TODO: 9/21/2023 registri:incrementare ip, setare ip dupamjump
        // TODO: 9/21/2023 refacere partea de decodificare 
    }
}
