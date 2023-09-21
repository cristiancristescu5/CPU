package org.example.Utils;

import java.util.Arrays;

public class OpUtils {//op codes
    private final static String[] OPCODES = new String[]{"010001", "010000", "001111",
            "001110", "001101", "001100",
            "001011", "001010", "001001",
            "000111", "000110", "000101",
            "000100", "000011", "000001", "000010"};

    public static String matchOP(String op) {
        for(String s : OPCODES){
            if(op.equals(s)){
                return s;
            }
        }
        return null;
    }
}
