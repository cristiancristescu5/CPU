package org.example.Components;

public class Clock {
    private static Integer clock = 0;
    public static void executeClock(){
        synchronized(clock){
            clock++;
        }
    }
}
