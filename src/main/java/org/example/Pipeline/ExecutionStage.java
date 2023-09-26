package org.example.Pipeline;

import org.example.Components.Executor;
import org.example.Exceptions.InvalidInstructionFormatException;
import org.example.Queues.ExecutionQueue;
import org.example.Queues.StoreQueue;
import org.example.Utils.StageSignals;

// TODO: 9/26/2023 sa iau in calcul jump ul, am pus la sembnale si semnal pt jump
public class ExecutionStage implements Runnable {
    private final Executor executor = new Executor();

    private void stop() {
        StageSignals.stopExecuteStage();
    }

    private void start() {
        StageSignals.startExecuting();
    }

    @Override
    public void run() {
        start();
        while (StageSignals.isExecuteRunning()) {
            if (!StoreQueue.isEmpty()) {
                try {

                    executor.execute(ExecutionQueue.popInstruction());
                } catch (InvalidInstructionFormatException e) {
                    stop();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
