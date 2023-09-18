package org.example.Components;

public class Clock {
    private static int cycle = 0;
    public static void incClock(){
        cycle++;
    }
    public static int getCycle(){
        return cycle;
    }
}
