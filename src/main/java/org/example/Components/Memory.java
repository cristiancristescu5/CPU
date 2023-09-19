package org.example.Components;

public class Memory {
    private final short SIZE;
    private long[] memory;

    public Memory(short size) {
        SIZE = size;
        memory = new long[SIZE];
        for (int i = 0; i < SIZE; i++) {
            memory[i] = 0;
        }
    }

    public short getSIZE() {
        return SIZE;
    }

    public long getFromAddress(short index){
        if(index < SIZE){
            return memory[index];
        }
        throw new IllegalArgumentException("Index too big");
    }
}
