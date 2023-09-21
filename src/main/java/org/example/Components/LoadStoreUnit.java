package org.example.Components;

import org.example.Memory.Memory;

public class LoadStoreUnit {
    private final Registers registers = new Registers((short) 128);

    public void reset() {
        registers.reset();
        Memory.reset();
    }

    public short loadFromRegister(int index) throws IllegalArgumentException {
        return registers.loadFromRegister(index);
    }

    public void storeToRegister(int index, short data) {
        registers.storeToRegister(index, data);
    }

    public short getInstructionPointer() {
        return registers.getInstructionPointer();
    }

    public short[] getFlags() {
        return registers.getFlags();
    }

    public short getStackPointer() {
        return registers.getStackPointer();
    }

    public void loadFromMemoryIntoRegister(int index, short addr) {
        String data = String.valueOf(Memory.loadFromAddress(addr, (short) 1)).substring(12, 15);
        short numData = Integer.valueOf(data).shortValue();
        registers.storeToRegister(index, numData);
    }

    public void storeToMemoryFromRegister(int index, short addr) {
        Memory.storeToAddress(addr, registers.loadFromRegister(index));
    }

    public void setStackPointer(short addr) {
        registers.setStackPointer(addr);
    }
}
