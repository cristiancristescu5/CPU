package org.example.Queues.DataRepresentation;

import org.example.Instructions.Instruction;

public class DecodedData {
    private final Instruction instruction;
    private final boolean needsOp1;
    private final boolean needOp2;
    private short value;

    public DecodedData(Instruction instruction, boolean needsOp1, boolean needOp2) {
        this.instruction = instruction;
        this.needsOp1 = needsOp1;
        this.needOp2 = needOp2;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public boolean isNeedOp2() {
        return needOp2;
    }

    public boolean isNeedsOp1() {
        return needsOp1;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }
}
