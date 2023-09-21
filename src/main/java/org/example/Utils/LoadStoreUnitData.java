package org.example.Utils;

public class LoadStoreUnitData {
    private short numOfWords;
    private long data;

    public LoadStoreUnitData(short numOfWords, long data) {
        this.numOfWords = numOfWords;
        this.data = data;
    }

    public long getData() {
        return data;
    }

    public short getNumOfWords() {
        return numOfWords;
    }
}
