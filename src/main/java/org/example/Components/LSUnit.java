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

    public static short loadFromRegister(int index){//executor
        return registers.loadFromRegister(index);
    }
    public static void storeToRegister(int index, short data){//executor
        registers.storeToRegister(index, data);
    }
    public static short getInstructionPointer(){//executor
        return registers.getInstructionPointer();
    }
    public static void setInstructionPointer(short ip){//executor
        registers.setInstructionPointer(ip);
    }
    public static Map<Short, Short> loadFromMemory(short addr){//ramane
        return Memory.load(addr);
    }
    public static void storeToMemory(Map<Short, Short> data){//ramane
        Memory.store(data);
    }
    public static void setFlags(short[] flags){//executor
        registers.setFlags(flags);
    }
    public static void setZeroFlag(short[] flags){
        registers.setZFlag(flags);
    }
    public static short[] getFlags(){
        return registers.getFlags();
    }
    public static String getInstruction(short addr){//ramane
        return Memory.loadInstruction(addr, (short) 4);
    }

}
