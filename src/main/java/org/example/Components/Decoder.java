package org.example.Components;

import org.example.Instructions.Instruction;
import org.example.Queues.DataRepresentation.DecodedData;
import org.example.Queues.ExecutionQueue;
import org.example.Utils.OpUtils;
import org.example.Utils.Utils;

public class Decoder {
    public void decode(String instruction) {
        int instructionValue = Integer.parseInt(instruction, 16);
        String representation = Utils.getBinaryRepresentation(instructionValue, 16);
        String op = representation.substring(0, 6);
        String src1 = representation.substring(6, 11);
        String src2 = representation.substring(11);
        boolean needsOp1 = false;
        boolean needsOp2 = false;
        if (op.equals("000111") || op.equals("001001") || op.equals("001010") ||
                op.equals("001011") || op.equals("001100")) {
            needsOp1 = true;
        }
        ExecutionQueue.addInstruction(new DecodedData(new Instruction(op, src1, src2, null, null)
                , needsOp1
                , needsOp2));
    }
}
