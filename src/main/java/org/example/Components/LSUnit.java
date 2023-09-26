package org.example.Components;

import org.example.Memory.Memory;

import java.util.HashMap;
import java.util.Map;

public class LSUnit {
    private static final Registers registers = new Registers((short) 128);
    public static void reset(){
        registers.reset();
        Memory.reset();
    }

    public static short loadFromRegister(short index){
        return registers.loadFromRegister(index);
    }
    public static void storeToRegister(short index, short data){
        registers.storeToRegister(index, data);
    }
    public static short getInstructionPointer(){
        return registers.getInstructionPointer();
    }
    public static void setInstructionPointer(short ip){
        registers.setInstructionPointer(ip);
    }
    public static Map<Short, Short> loadFromMemory(short addr){
        return Memory.load(addr);
    }
    public static void storeToMemory(Map<Short, Short> data){
        Memory.store(data);
    }
    public static void loadFromMemoryIntoRegister(short index, short addr){
        registers.storeToRegister(index, Memory.load(addr).get(addr));
    }
    public static void loadFromRegisterIntoMemory(short index, short addr){
        Map<Short, Short> data = new HashMap<>();
        data.put(addr, registers.loadFromRegister(index));
        data.put((short) (addr-2), null);
        data.put((short) (addr-4), null);
        data.put((short) (addr-6), null);
        Memory.store(data);
    }
    public static String getInstruction(short addr){
        return Memory.loadInstruction(addr, (short) 4);
    }

}
