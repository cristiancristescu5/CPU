package org.example.Memory;

import org.example.Utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Memory {
    private static final Map<Short, Short> memory = new HashMap<>();

    static {
        for (short i = Short.MIN_VALUE; i < Short.MAX_VALUE; i++) {
            if (i % 2 == 0) {
                memory.put(i, (short) 0);
            }
        }
    }

    public static void storeInstructions(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            short i = Integer.valueOf("fff0", 16).shortValue();
            while (scanner.hasNext()) {
                memory.put(i, Integer.valueOf(scanner.nextLine(), 16).shortValue());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File does not exist!");
        }
    }

    public static void reset() {//resetting all of the addresses
        for (short i = Short.MIN_VALUE; i < Short.MAX_VALUE; i++) {
            memory.put(i, (short) 0);
        }
    }

    public static String loadInstruction(short addr, short numOfWords) {//max 4 words
        StringBuilder strInstruction = new StringBuilder();
        if (numOfWords > 4) {
            throw new IllegalArgumentException("Invalid number of instructions");
        }
        if (addr % 2 != 0 || addr < Integer.valueOf("fff0", 16).shortValue()) {
            throw new IllegalArgumentException("Invalid instruction address");
        }
        for(int i = 0 ; i < numOfWords ; i++){
            strInstruction.append(Utils.getBinaryRepresentation(memory.get((short)(addr + 2 * i)), 16));
        }
        return strInstruction.toString();
    }
    public static void store(Map<Short, Short> data) {
        for (Short s : data.keySet()) {
            if (s % 2 != 0) {
                throw new IllegalArgumentException("Address must be even!");
            }
            if (data.get(s) != null) {
                memory.put(s, data.get(s));
            }
        }
    }

    public static Map<Short, Short> load(short address) {
        if (address % 2 != 0) {
            throw new IllegalArgumentException("Address must be even.");
        }
        Map<Short, Short> data = new HashMap<>();
        for (short i = 0, addr = address; i < 4; i++, addr -= 2) {
            data.put(addr, memory.get(addr));
        }
        return data;
    }
}
