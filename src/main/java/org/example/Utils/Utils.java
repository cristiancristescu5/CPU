package org.example.Utils;

public class Utils {
    public static String getBinaryRepresentation(int x, int size) {
        char[] number = new char[size];
        int y = x;
        for (int i = size - 1; i >= 0; i--) {
            number[i] = Integer.toString(y%2, 2).charAt(0);
            y /= 2;
        }
        return String.valueOf(number);
    }
}
