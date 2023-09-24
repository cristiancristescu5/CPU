package org.example.Memory;

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

    public static long loadInstruction(short addr, short numOfWords) {//max 3 words
        long instruction = 0;
        if (numOfWords > 4) {
            throw new IllegalArgumentException("Invalid number of instructions");
        }
        if (addr % 2 != 0 || addr < Integer.valueOf("fff0", 16).shortValue()) {
            throw new IllegalArgumentException("Invalid instruction address");
        }
        for (int i = 0; i < numOfWords; i++) {
            instruction += ((long) memory.get((short)(addr + 2 * i)) & 0xFFFF) << (i * 16);
        }
        return instruction;
    }

    public static long loadFromAddress(short addr, short numOfWords) {
        long data = 0;
        if (numOfWords > 4) {
            throw new IllegalArgumentException("Invalid number of instructions");
        }
        if (addr % 2 != 0 || addr >= Integer.valueOf("fff0", 16).shortValue()) {
            throw new IllegalArgumentException("Invalid memory address");
        }
        for (int i = 0; i < numOfWords; i++) {
            System.out.println("addr: " + Short.toString((short) (addr - 2 * i)));
            data += ((long) memory.get((short) (addr - 2 * i)) & 0xFFFF) << (i * 16);
        }

        return data;
    }

    public static void storeToAddress(short addr, short data) {
        if (addr % 2 != 0 || addr >= Integer.valueOf("fff0", 16).shortValue()) {
            throw new IllegalArgumentException("Invalid data memory address.");
        }
        System.out.println("addr: " + addr + " data: " + data);
        memory.put(addr, data);
    }
}
