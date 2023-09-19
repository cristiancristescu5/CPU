package org.example;

import java.util.BitSet;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static BitSet bitSet = new BitSet();
    public static void main(String[] args) {
        int x = 32;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Utils.getBinaryRepresentation(2, 64));
    }
}