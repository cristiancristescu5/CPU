package org.example.Queues;

import org.example.Memory.MemoryData;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MemoryQueue {//singleton, for writing into memory
    private static BlockingQueue<MemoryData> memoryData = new LinkedBlockingQueue<>();

    public static void addInMemory(MemoryData mem){
        try{
            memoryData.offer(mem, 10, TimeUnit.SECONDS);//adaug if
        }catch (InterruptedException e){
            throw new IllegalStateException("Waited too many seconds for memory.");
        }
    }
    public static MemoryData popMemoryData(){
        try{
            return memoryData.poll(10, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            throw new IllegalStateException("Waited too many seconds for memory.");
        }
    }
}
