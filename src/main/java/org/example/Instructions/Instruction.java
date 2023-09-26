package org.example.Instructions;

import org.example.Memory.Memory;

import java.util.Map;
// e gata
public class Instruction {
    private final String op;
    private final String src1;
    private final String src2;
    private String optionalParam1;
    private String optionalParam2;
    private boolean isComplete;
    private Map<Short, Short> fromMemory1 = null;
    private Map<Short, Short> fromMemory2 = null;

    public Instruction(String op, String src1, String src2, String optionalParam1, String optionalParam2, boolean isComplete) {
        this.op = op;
        this.src1 = src1;
        this.src2 = src2;
        this.optionalParam1 = optionalParam1;
        this.optionalParam2 = optionalParam2;
        this.isComplete = isComplete;
    }

    public String getOp() {
        return op;
    }

    public String getSrc1() {
        return src1;
    }

    public String getSrc2() {
        return src2;
    }

    public String getOptionalParam1() {
        return optionalParam1;
    }

    public String getOptionalParam2() {
        return optionalParam2;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setOptionalParam1(String optionalParam1) {
        this.optionalParam1 = optionalParam1;
    }

    public void setOptionalParam2(String optionalParam2) {
        this.optionalParam2 = optionalParam2;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Map<Short, Short> getFromMemory1() {
        return fromMemory1;
    }

    public Map<Short, Short> getFromMemory2() {
        return fromMemory2;
    }

    public void setFromMemory1(Map<Short, Short> fromMemory1) {
        this.fromMemory1 = fromMemory1;
    }

    public void setFromMemory2(Map<Short, Short> fromMemory2) {
        this.fromMemory2 = fromMemory2;
    }
}
