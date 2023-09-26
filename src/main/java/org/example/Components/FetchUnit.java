package org.example.Components;

import org.example.Queues.InstructionQueue;
import org.example.Utils.Utils;

public class FetchUnit {
    private short ip;

    public FetchUnit() {
        ip = Integer.valueOf("fff0").shortValue();
    }

    public void setIp(short ip) {
        this.ip = ip;
    }
    public void incrementIP() {
        ip = (short) (ip + 8);
    }

    public void fetch() {
        String instruction = LSUnit.getInstruction(ip);
        InstructionQueue.addInstruction(instruction);
        System.out.println(ip + ": " + instruction);
        incrementIP();
    }
}
