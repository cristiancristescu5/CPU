package org.example.Components;

import org.example.Instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

public class Decoder {
    private final List<String> OPParams = new ArrayList<>();

    public Decoder() {
        OPParams.add("10000");
        OPParams.add("10001");
        OPParams.add("10010");
    }

    public Instruction decode(String instruction) {//16 bits
        String op = instruction.substring(0, 6);//op 6 bits
        String src1 = instruction.substring(6, 12);//src1 5 bits
        String src2 = instruction.substring(12);//src2 5 bits
        if (OPParams.contains(src1) || OPParams.contains(src2)) {
            return new Instruction(op, src1, src2, null, null, false);//if I need optional params, i set the completion flag to false
        }
        return new Instruction(op, src1, src2, null, null, true); // else my instruction is complete
    }

    public void completeInstruction(Instruction decodedInstruction, String s) {
        if (OPParams.contains(decodedInstruction.getSrc1()) && decodedInstruction.getOptionalParam1() == null) {//daca am parametru 1 optional si null
            decodedInstruction.setOptionalParam1(s);//completez op1
            if (!OPParams.contains(decodedInstruction.getSrc2()) || decodedInstruction.getOptionalParam2() != null) { // daca nu am src2 ca par optional sau op2 nu este null, atunci e completa
                decodedInstruction.setComplete(true);
            } else {
                if (OPParams.contains(decodedInstruction.getSrc2())) { // daca am src2 din OPParams, atunci instructiunea nu e completa
                    decodedInstruction.setComplete(false);
                }
            }
        } else {
            if (OPParams.contains(decodedInstruction.getSrc2()) && decodedInstruction.getOptionalParam2() == null) {
                decodedInstruction.setOptionalParam2(s);
                if (!OPParams.contains(decodedInstruction.getSrc1()) || decodedInstruction.getOptionalParam1() != null) {
                    decodedInstruction.setComplete(true);
                } else {
                    if (OPParams.contains(decodedInstruction.getSrc1())) {
                        decodedInstruction.setComplete(false);
                    }
                }
            }
        }
    }
}
