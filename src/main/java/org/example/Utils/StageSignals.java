package org.example.Utils;

public class StageSignals {
    private static boolean fetchRunning = false, decodeRunning = false, executeRunning = false;
    private static boolean loadRunning = false, storeRunning = false;
    private static boolean isJump = false;

    public static void stopFetchStage() {
        fetchRunning = false;
    }

    public static void stopDecodingStage() {
        decodeRunning = false;
    }

    public static void stopExecuteStage() {
        executeRunning = false;
    }


    public static void startFetching() {
        fetchRunning = true;
    }

    public static void startDecoding() {
        decodeRunning = true;
    }

    public static void startExecuting() {
        executeRunning = true;
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

    public static boolean isLoadRunning() {
        return loadRunning;
    }
    public static boolean isStoreRunning(){
        return storeRunning;
    }
    public static void startLoading(){
        loadRunning = true;
    }
    public static void startStoring(){
        storeRunning = true;
    }
    public static void stopLoading(){
        loadRunning = false;
    }
    public static void stopStoring(){
        storeRunning = false;
    }
    public static void executeJump(){
        isJump = true;
    }
    public static boolean isJump(){
        return isJump;
    }
    public static void resetJump(){
        isJump = false;
    }

    public static void stopEverything(){
        stopLoading();
        stopStoring();
        stopFetchStage();
        stopExecuteStage();
        stopDecodingStage();
        resetJump();
    }
}
