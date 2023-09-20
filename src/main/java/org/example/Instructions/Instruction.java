package org.example.Instructions;

public class Instruction {
    private final String op;
    private final String src1;
    private final String src2;
    private String optionalParam1;
    private String optionalParam2;

    public Instruction(String op, String src1, String src2) {
        this.op = op;
        this.src1 = src1;
        this.src2 = src2;
    }

    public Instruction(String op, String src1, String src2, String optionalParam1, String optionalParam2) {
        this.op = op;
        this.src1 = src1;
        this.src2 = src2;
        this.optionalParam1 = optionalParam1;
        this.optionalParam2 = optionalParam2;
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
}
