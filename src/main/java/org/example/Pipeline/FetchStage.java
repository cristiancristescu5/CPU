package org.example.Pipeline;

import org.example.Components.FetchUnit;
import org.example.Utils.StageSignals;

public class FetchStage implements Runnable {
    FetchUnit fetchUnit = new FetchUnit();

    private void stop() {
        StageSignals.stopFetchStage();
    }

    @Override
    public void run() {
        StageSignals.startFetching();
        while (StageSignals.isFetchRunning()) {
            fetchUnit.fetch();
        }
    }
}
