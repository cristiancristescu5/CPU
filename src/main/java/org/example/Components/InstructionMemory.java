package org.example.Components;

import org.example.Exceptions.InvalidNumberOfInstructions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class InstructionMemory {
    private final int SIZE;
    private final String filePath;
    private final String[] instructions;
    private int numInstr=0;
    public InstructionMemory(int size, String filePath) throws InvalidNumberOfInstructions {
        this.SIZE = size;
        this.filePath= filePath;
        instructions = new String[SIZE];
        try{
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            int i = 0;
            while(scanner.hasNext()){
                numInstr++;
                if(numInstr > SIZE){
                    throw new InvalidNumberOfInstructions("Too many instructions");
                }
                instructions[i] = scanner.nextLine();
                i++;
            }
            scanner.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());

        }
    }

    public int getSIZE() {
        return SIZE;
    }

    public int getNumInstr() {
        return numInstr;
    }

    public String getFilePath() {
        return filePath;
    }
    public String getInstruction(int index){
        if(index/4 <=numInstr-1){
            return instructions[index/4];
        }else{
            throw new IllegalArgumentException("Index too big");
        }
    }
    public void reset(){
        Arrays.fill(instructions, 0 , SIZE-1, null);
    }
}
