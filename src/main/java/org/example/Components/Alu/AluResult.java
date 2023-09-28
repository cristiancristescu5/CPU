package org.example.Components.Alu;

public class AluResult {
    private Short result;
    private short[] flags;
    private Short r1;
    private Short r0;

    public AluResult(Short result, short[] flags, Short r1, Short r0) {
        this.result = result;
        this.flags = flags;
        this.r1 = r1;
        this.r0 = r0;
    }

    public short[] getFlags() {
        return flags;
    }

    public Short getR0() {
        return r0;
    }

    public Short getR1() {
        return r1;
    }

    public Short getResult() {
        return result;
    }
}
