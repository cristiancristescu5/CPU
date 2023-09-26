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

}
