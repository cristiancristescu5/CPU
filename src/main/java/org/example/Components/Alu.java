package org.example.Components;

import org.example.Utils;

public class Alu {
    private static short zero;
    private static short equal;
    private static short greater;
    private static short r0;
    private static short r1;

    public static short add(short a, short b) {
        short sum = (short) (a + b);
        zero = (short) ((sum == 0) ? 1 : 0);
        return sum;
    }

    public static short sub(short a, short b) {
        short sub = (short) (a - b);
        zero = (short) ((sub == 0) ? 1 : 0);
        return sub;
    }

    public static void mul(short a, short b) {
        int prod = a * b;
        zero = (short) ((prod == 0) ? 1 : 0);
        String prodRepresentation = Utils.getBinaryRepresentation(prod, 32);
        r0 = Short.parseShort(prodRepresentation.substring(0, 15));
        r1 = Short.parseShort(prodRepresentation.substring(16, 31));
    }

    public static void div(short a, short b) {
        if(b == 0){
            throw  new IllegalArgumentException("Division by 0");
        }
        if(a == 0){
            zero = 0;
        }
        r0 = (short) (a / b);
        r1 = (short) (a % b);
    }

    public static void cmp(short a, short b) {
        if (a == 0 && b == 0) {
            zero = 1;
            equal = 1;
            greater = 0;
            return;
        }
        if (a == b) {
            zero = 0;
            equal = 1;
            greater = 0;
            return;
        }
        if(a > b) {
            zero = 0;
            equal = 0;
            greater = 1;
        }
    }

    public static short getR0() {
        return r0;
    }

    public static short getR1() {
        return r1;
    }

    public static int getEqual() {
        return equal;
    }

    public static int getGreater() {
        return greater;
    }

    public static int getZero() {
        return zero;
    }
}
