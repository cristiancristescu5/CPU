package org.example.Pipeline;

import org.example.Components.Executor;
import org.example.Exceptions.InvalidInstructionFormatException;
import org.example.Queues.ExecutionQueue;
import org.example.Utils.StageSignals;

public class ExecutionStage implements Runnable {
    private final Executor executor = new Executor();

    private void stop() {
        StageSignals.startExecuting();
    }

    @Override
    public void run() {
        StageSignals.startExecuting();
        while (StageSignals.isExecuteRunning()) {
            try {
                executor.execute(ExecutionQueue.popInstruction());
            } catch (InvalidInstructionFormatException e) {
                stop();
                throw new RuntimeException(e);
            }
        }
    }
}
