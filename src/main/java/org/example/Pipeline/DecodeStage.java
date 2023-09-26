package org.example.Pipeline;

import org.example.Components.Decoder;
import org.example.Instructions.Instruction;
import org.example.Queues.InstructionQueue;
import org.example.Queues.LoadQueue;
import org.example.Queues.StoreQueue;
import org.example.Utils.StageSignals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// TODO: 9/26/2023 pt operatii de load, am un buffer ce retine instructiunile mai intai, apoi fac load ul, apoi bag in coada de executie de mai trebuie, apoi in coada de store
// TODO: 9/25/2023 sa verific ce si cum fac ca sa redirectionez instructiuni catre memoryQueue mai intai si apoi catre execution queue, sa vad cum pastreaz ordinea
public class DecodeStage implements Runnable {
    private final Decoder decoder = new Decoder();
    private Queue<Instruction> incompleteInstructions;
    List<String> optionalParams = Arrays.asList("10001", "10010");
    public DecodeStage() {
        incompleteInstructions = new LinkedList<>();
    }

    private void stop() {
        StageSignals.stopDecodingStage();
    }
    private void start(){
        StageSignals.startDecoding();
    }
    @Override
    public void run() {
        start();
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
//                            if (optionalParams.contains(decodedInstruction.getSrc1()) || optionalParams.contains(decodedInstruction.getSrc2())) {
                                LoadQueue.push(decodedInstruction);
//                            } else {
//                                ExecutionQueue.addInstruction(decodedInstruction);
//                            }
                        }
                    } else {
                        Instruction incompleteInstruction = incompleteInstructions.poll();//daca exista instructiuni incomplete le completez cu ce mai am din acest fetch window
                        decoder.completeInstruction(incompleteInstruction, instructionList[i]);//maxim 2 pasi de completie, primul pas
                        if (!incompleteInstruction.isComplete() && i < 3) {
                            i++;
                            decoder.completeInstruction(incompleteInstruction, instructionList[i]); // al doilea pas daca e necesar
                        }
                        if (incompleteInstruction.isComplete()) {
//                            if (optionalParams.contains(incompleteInstruction.getSrc1()) || optionalParams.contains(incompleteInstruction.getSrc2())) {
                                LoadQueue.push(incompleteInstruction);
//                            } else {
//                                ExecutionQueue.addInstruction(incompleteInstruction);
//                            }
                        } else {
                            incompleteInstructions.add(incompleteInstruction);
                        }
                    }
                }
            }
        }
    }
}
