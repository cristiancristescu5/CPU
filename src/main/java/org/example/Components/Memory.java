package org.example.Components;

public class Memory {
    private final int SIZE;
    private long[] memory;

    public Memory(int size) {
        SIZE = size;
        memory = new long[SIZE];
        for (int i = 0; i < SIZE; i++) {
            memory[i] = 0;
        }
    }

    public int getSIZE() {
        return SIZE;
    }

    public long getFromAddress(int index){
        if(index < SIZE){
            return memory[index];
        }
        throw new IllegalArgumentException("Index too big");
    }
}
