package org.example.Queues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class InstructionQueue {//singleton
    private static BlockingQueue<String> instructions = new LinkedBlockingQueue<>();//thread safe

    public static void addInstruction(String instruction){
        try{
            instructions.offer(instruction, 10, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            throw new IllegalStateException("Waited too many seconds in order to insert into the Instruction Queue.");
        }
    }
    public static String popInstruction(){
        try{
            return instructions.poll(10, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            throw new IllegalStateException("Waited too many seconds in order to extract from the Instruction Queue.");
        }
    }
}
