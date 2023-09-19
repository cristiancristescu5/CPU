package org.example.Components;

import java.util.Arrays;

public class Memory {
    private final short SIZE;
    private short[] memory;

    public Memory(short size) {
        SIZE = size;
        memory = new short[SIZE];
        for (int i = 0; i < SIZE; i++) {
            memory[i] = 0;
        }
    }

    public short getSIZE() {
        return SIZE;
    }

    public short loadFromAddress(short index){
        return memory[index];
    }
    public void storeToAddress(short index, short information){
        memory[index] = information;
    }

    public void reset(){
        Arrays.fill(memory, 0, SIZE-1, (short) 0);
    }
}
