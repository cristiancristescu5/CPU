package org.example.Pipeline;

import org.example.Components.LSUnit;
import org.example.Instructions.Instruction;
import org.example.Queues.ExecutionQueue;
import org.example.Queues.LoadQueue;
import org.example.Utils.StageSignals;

//e gata
public class LoadStage implements Runnable {
    private void stop() {
        StageSignals.stopLoading();
    }

    private void start() {
        StageSignals.startLoading();
    }

    private boolean isRunning() {
        return StageSignals.isLoadRunning();
    }

    @Override
    public void run() {
        start();
        while (isRunning()) {
            if (!LoadQueue.isEmpty()) {
                Instruction i = LoadQueue.pop();
                if (i != null) {
                    if (i.getSrc1().equals("10001")) {
                        i.setFromMemory1(LSUnit.loadFromMemory(Integer.valueOf(i.getOptionalParam1(), 2).shortValue()));
                    }
                    if (i.getSrc1().equals("10010")) {
                        i.setFromMemory1(LSUnit.loadFromMemory(LSUnit.loadFromRegister(Integer.valueOf(i.getOptionalParam1(), 2).shortValue())));//scot din memorie de la dresa continuta de registrul din op1
                    }
                    if (i.getSrc2().equals("10001")) {
                        i.setFromMemory2(LSUnit.loadFromMemory(Integer.valueOf(i.getOptionalParam2(), 2).shortValue()));
                    }
                    if (i.getSrc2().equals("10010")) {
                        i.setFromMemory2(LSUnit.loadFromMemory(LSUnit.loadFromRegister(Integer.valueOf(i.getOptionalParam2(), 2).shortValue())));//scot din memorie de la dresa continuta de registrul din op1
                    }
                }
                ExecutionQueue.addInstruction(i);
            }
        }
    }
}
