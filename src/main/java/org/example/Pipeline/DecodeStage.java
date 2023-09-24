package org.example.Pipeline;

import org.example.Components.Decoder;
import org.example.Instructions.Instruction;
import org.example.Queues.ExecutionQueue;
import org.example.Queues.InstructionQueue;
import org.example.Utils.StageSignals;

import java.util.LinkedList;
import java.util.Queue;

public class DecodeStage implements Runnable {
    private final Decoder decoder = new Decoder();
    private Queue<Instruction> incompleteInstructions;
    public DecodeStage(){
        incompleteInstructions = new LinkedList<>();
    }
    private void stop() {
        StageSignals.startDecoding();
    }

    @Override
    public void run() {
        StageSignals.startDecoding();
        while (StageSignals.isDecodeRunning()) {
            String instructions = InstructionQueue.popInstruction();
            if (instructions == null) {
                stop();
            } else {
                String[] instructionList = new String[]{
                        instructions.substring(0, 16),
                        instructions.substring(16, 32),
                        instructions.substring(32, 48),
                        instructions.substring(48)
                };
                for (int i = 0; i < 4; i++) {
                    if (incompleteInstructions.isEmpty()) {//daca nu exista instructiuni incomplete
                        Instruction decodedInstruction = decoder.decode(instructionList[i]);//decodez
                        while (!decodedInstruction.isComplete() && i < 3) {//completez instructiunea daca e nevoie
                            decoder.completeInstruction(decodedInstruction, instructionList[i + 1]);//completez instructiuni
                            i++;
                        }
                        if (!decodedInstruction.isComplete()) {
                            incompleteInstructions.add(decodedInstruction);//adaug instructiunile incomplete
                        } else {
                            ExecutionQueue.addInstruction(decodedInstruction);
                        }
                    } else {
                        Instruction incompleteInstruction = incompleteInstructions.poll();//daca exista instructiuni incomplete le completez cu ce mai am din acest fetch window
                        decoder.completeInstruction(incompleteInstruction, instructionList[i]);//maxim 2 pasi de completie, primul pas
                        if (!incompleteInstruction.isComplete() && i < 3) {
                            decoder.completeInstruction(incompleteInstruction, instructionList[i + 1]); // al doilea pas daca e necesar
                        }
                        if(incompleteInstruction.isComplete()) {
                            ExecutionQueue.addInstruction(incompleteInstruction);
                        }else {
                            incompleteInstructions.add(incompleteInstruction);
                        }
                    }
                }
            }
        }
    }
}
