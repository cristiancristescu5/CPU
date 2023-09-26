package org.example.Components;

import org.example.Components.Alu.Alu;
import org.example.Exceptions.InvalidInstructionFormatException;
import org.example.Instructions.Instruction;
import org.example.Utils.StageSignals;

// TODO: 9/25/2023 execut doar daca coada de memorie e goala
//am rezolvat partea de load si store si cum aduc instructiunile la executie
public class Executor{//singleton
    private final Alu alu = new Alu();
    public void execute(Instruction instruction) throws InvalidInstructionFormatException {
        if(!instruction.isComplete()){
            throw new InvalidInstructionFormatException("Incomplete instruction");//arunc exceptie daca instructiunea nu e completa
        }
        if(instruction.getOp().equals("001111")){
            StageSignals.stopEverything();

        }

    }
}
