package org.example.Queues;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.example.Instructions.Instruction;

public class ExecutionQueue {
    private static BlockingQueue<Instruction> instructions = new LinkedBlockingQueue<>();

    public static void addInstruction(Instruction instruction){
        try {
            instructions.offer(instruction, 10, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            throw new IllegalStateException("Waited too many time in order to add instructions in the execution queue!");
        }
    }
    public static Instruction popInstruction(){
        try{
            return instructions.poll(10, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            throw new IllegalStateException("Waited too many time in order to fetch data from execution queue");
        }
    }
    public static void emptyQueue(){
        while(!instructions.isEmpty()){
            instructions.poll();
        }
    }
}
