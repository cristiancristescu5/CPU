package org.example.Memory;

public class MemoryData {
    private short address;
    private short data;

    public MemoryData(short address, short data) {
        this.address = address;
        this.data = data;
    }

    public short getAddress() {
        return address;
    }

    public short getData() {
        return data;
    }
}
