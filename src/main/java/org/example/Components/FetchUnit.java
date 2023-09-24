package org.example.Components;

import org.example.Queues.InstructionQueue;
import org.example.Utils.Utils;

public class FetchUnit {
    private short ip;

    public FetchUnit() {
        ip = Integer.valueOf("fff0").shortValue(); // -16
    }

    public void incrementIP() {
        ip = (short) (ip + 8);
    }

    public void fetch() {
        InstructionQueue.addInstruction(Utils.getBinaryRepresentation(LoadStoreUnit.getInstruction(), 64));
        incrementIP();
    }
}
