package org.example.Queues;

import org.example.Instructions.Instruction;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class StoreQueue {//singleton, for writing into memory
    private static BlockingQueue<Instruction> instructions = new LinkedBlockingQueue<>();

    public static void addInMemory(Instruction mem){
        try{
            instructions.offer(mem, 10, TimeUnit.SECONDS);//adaug if
        }catch (InterruptedException e){
            throw new IllegalStateException("Waited too many seconds for memory.");
        }
    }
    public static Instruction popInstruction(){
        try{
            return instructions.poll(10, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            throw new IllegalStateException("Waited too many seconds for memory.");
        }
    }
    public static boolean isEmpty(){
        return instructions.isEmpty();
    }
    public static void emptyQueue(){
        while(!instructions.isEmpty()){
            instructions.poll();
        }
    }
}
