package org.example.Utils;

public class StageSignals {
    private static boolean fetchRunning = false
            , decodeRunning = false
            , executeRunning = false
            , lsRunning = false;
    public static void stopFetchStage(){
        fetchRunning = false;
    }
    public static void stopDecodingStage(){
        decodeRunning = false;
    }
    public static void stopExecuteStage(){
        executeRunning = false;
    }
    public static void stopLsStage(){
        lsRunning = false;
    }
    public static void startFetching(){
        fetchRunning = true;
    }
    public static void startDecoding(){
        decodeRunning = true;
    }
    public static void startExecuting(){
        executeRunning = true;
    }
    public static void startLs(){
        lsRunning = true;
    }

    public static boolean isFetchRunning() {
        return fetchRunning;
    }

    public static boolean isDecodeRunning() {
        return decodeRunning;
    }

    public static boolean isExecuteRunning() {
        return executeRunning;
    }

    public static boolean isLsRunning() {
        return lsRunning;
    }
}
