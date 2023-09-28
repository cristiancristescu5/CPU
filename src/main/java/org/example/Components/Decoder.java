package org.example.Components;

import org.example.Exceptions.InvalidInstructionFormatException;
import org.example.Instructions.Instruction;
import org.example.Utils.OpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Decoder {
    private final List<String> OPParams = Arrays.asList("10000", "10001", "10010");
    public List<String> getOPParams() {
        return OPParams;
    }

    public Instruction decode(String instruction) throws InvalidInstructionFormatException {//16 bits
        String op = OpUtils.matchOP(instruction.substring(0, 6));//op 6 bits
        if(op == null){
            throw new InvalidInstructionFormatException("Invalid op!");
        }
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
