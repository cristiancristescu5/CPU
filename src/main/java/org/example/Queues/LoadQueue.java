package org.example.Queues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.example.Instructions.Instruction;

public class LoadQueue {
    private static BlockingQueue<Instruction> instructions = new LinkedBlockingQueue<>();

    public static void push(Instruction i) {
        try {
            instructions.offer(i, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Waited too many seconds for memory");
        }
    }

    public static Instruction pop() {
        try {
            return instructions.poll(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Waited too many time for memory.");
        }
    }

    public static boolean isEmpty() {
        return instructions.isEmpty();
    }
    public static void emptyQueue(){
        while(!instructions.isEmpty()){
            instructions.poll();
        }
    }
}
