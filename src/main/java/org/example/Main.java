package org.example;
import org.example.Memory.Memory;

public class Main {
    public static void main(String[] args) {
        System.out.println(Integer.valueOf("8000", 16).shortValue());
        System.out.println(Integer.parseInt("fff0", 16));
        System.out.println(Integer.valueOf("10", 10).shortValue());
        Memory.storeInstructions("src\\main\\resources\\instructions.txt");
        Memory.storeToAddress((short) -18, (short) 0xFFFF);
        Memory.storeToAddress((short) -20, (short) 0xFFFF);
        Memory.storeToAddress((short) -22, (short) 0xFF11);
        System.out.printf("0x%016x%n", Memory.loadFromAddress((short) -18, (short) 1));
        System.out.println(Long.toHexString(-16).length());
        //        FetchStage fetchStage = new FetchStage();
//        DecodeStage decodeStage = new DecodeStage();
//        ExecutionStage executionStage = new ExecutionStage();
//        LoadStoreStage loadStoreStage = new LoadStoreStage();
//        Thread fetch = new Thread(fetchStage);
//        Thread decode = new Thread(decodeStage);
//        Thread execution = new Thread(executionStage);
//        Thread loadSore = new Thread(loadStoreStage);
//        fetch.start();
//        decode.start();
//        execution.start();
//        loadSore.start();
    }
}