package org.example.Pipeline;

import org.example.Components.LSUnit;
import org.example.Instructions.Instruction;
import org.example.Queues.StoreQueue;
import org.example.Utils.StageSignals;

public class StoreStage implements Runnable {

    private void stop(){
        StageSignals.stopStoring();
    }
    private void start(){
        StageSignals.startStoring();
    }
    @Override
    public void run() {
        start();
        while(StageSignals.isLoadRunning()){
            if(!StoreQueue.isEmpty()){
                Instruction i = StoreQueue.popInstruction();
                if(i.getFromMemory1()!=null){
                    LSUnit.storeToMemory(i.getFromMemory1());
                }
                if(i.getFromMemory2()!=null){
                    LSUnit.storeToMemory(i.getFromMemory2());
                }
            }
        }
    }
}
