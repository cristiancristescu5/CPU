package org.example.Components.Alu;

import jdk.jshell.execution.Util;
import org.example.Instructions.Instruction;
import org.example.Utils.Utils;


public class Alu {
    public AluResult compute(short op1, short op2, String op) {
        switch (op) {
            case "000001" -> {
                short res = (short) (op1 + op2);
                short[] flags = {0, 0, 0};
                if (res == 0) {
                    flags[0] = 1;
                }
                return new AluResult(res, flags, null, null);
            }
            case "000010" -> {
                short res = (short) (op1 - op2);
                if (op1 == op2) {
                    return new AluResult(res, new short[]{1, 0, 0}, null, null);
                } else {
                    return new AluResult(res, new short[]{0, 0, 0}, null, null);
                }
            }
            case "000100" -> {
                String res = Utils.getBinaryRepresentation(op1 * op2, 32);
                short r0 = Integer.valueOf(res.substring(0, 16), 2).shortValue();
                short r1 = Integer.valueOf(res.substring(16), 2).shortValue();
                if (r0 == 0 || r1 == 0) {
                    return new AluResult(null, new short[]{1, 0, 0}, r0, r1);
                } else {
                    return new AluResult(null, new short[]{0, 0, 0}, r0, r1);
                }
            }
            case "000101" -> {
                if (op2 == 0) {
                    throw new IllegalArgumentException("Division by 0!!!!!!!!!!");
                }
                short r0 = Integer.valueOf(Utils.getBinaryRepresentation(op1 / op2, 16), 2).shortValue();
                short r1 = Integer.valueOf(Utils.getBinaryRepresentation(op1 % op2, 16), 2).shortValue();
                if (op1 == 0) {
                    return new AluResult(null, new short[]{1, 0, 0}, r0, r1);
                } else {
                    return new AluResult(null, new short[]{0, 0, 0}, r0, r1);
                }
            }
            case "000110" -> {
                if (op1 == op2 && op1 == 0) {
                    return new AluResult(null, new short[]{1, 1, 0}, null, null);
                }
                if (op1 == op2) {
                    return new AluResult(null, new short[]{0, 1, 0}, null, null);
                }
                if (op1 > op2) {
                    return new AluResult(null, new short[]{0, 0, 1}, null, null);
                }
            }
        }
        return null;
    }
}
