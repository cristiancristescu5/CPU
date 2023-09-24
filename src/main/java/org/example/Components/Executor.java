package org.example.Components;

import org.example.Exceptions.InvalidInstructionFormatException;
import org.example.Instructions.Instruction;

public class Executor{//singleton
    private final Alu alu = new Alu();
    public void execute(Instruction instruction) throws InvalidInstructionFormatException {
        if(!instruction.isComplete()){
            throw new InvalidInstructionFormatException("Incomplete instruction");//arunc exceptie daca instructiunea nu e completa
        }
    }
}
