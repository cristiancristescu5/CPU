package org.example.Components;

import org.example.Components.Alu.Alu;
import org.example.Components.Alu.AluResult;
import org.example.Exceptions.InvalidInstructionFormatException;
import org.example.Instructions.Instruction;
import org.example.Queues.ExecutionQueue;
import org.example.Queues.InstructionQueue;
import org.example.Queues.LoadQueue;
import org.example.Queues.StoreQueue;
import org.example.Utils.StageSignals;

import java.util.Arrays;
import java.util.List;

public class Executor {
    private final Alu alu = new Alu();
    private final List<String> jumps = Arrays.asList("000111", "001001", "001010", "001011", "001100");

    private boolean isRegister(int reg) {
        return (reg >= 0 && reg <= 7);
    }

    private void executeRegisterRegister(Instruction i) throws InvalidInstructionFormatException {//reg reg
        if (jumps.contains(i.getOp())) {
            throw new InvalidInstructionFormatException("A jump can only have one destination");
        }
        if (i.getOp().equals("000011")) {
            LSUnit.storeToRegister(Integer.parseInt(i.getSrc1(), 2) - 1, LSUnit.loadFromRegister(Integer.parseInt(i.getSrc2(), 2) - 1));
            return;
        }//mov

        int reg1 = Integer.parseInt(i.getSrc1(), 2) - 1;
        int reg2 = Integer.parseInt(i.getSrc2(), 2) - 1;

        short op1 = LSUnit.loadFromRegister(Integer.parseInt(i.getSrc1(), 2) - 1);
        short op2 = LSUnit.loadFromRegister(Integer.parseInt(i.getSrc2(), 2) - 1);

        AluResult aluResult = alu.compute(op1, op2, i.getOp());

        if (i.getOp().equals("000110")) {
            LSUnit.setFlags(aluResult.getFlags());
        }//cmp

        if (aluResult.getR1() != null) {
            LSUnit.storeToRegister(0, aluResult.getR0());
            LSUnit.storeToRegister(1, aluResult.getR1());
            LSUnit.setZeroFlag(aluResult.getFlags());
            return;
        }//mul, div
        LSUnit.storeToRegister(reg1, aluResult.getResult());//add, sub
        LSUnit.setZeroFlag(aluResult.getFlags());
    }

    private void executeMemMem(Instruction i) throws InvalidInstructionFormatException {//mem mem
        String src1 = i.getSrc1();
        String src2 = i.getSrc2();
        if (src1.equals("10001")) {
            short addr1 = Integer.valueOf(i.getOptionalParam1()).shortValue();
            short op1 = i.getFromMemory1().get(addr1);
            short op2;
            if (src2.equals("10001")) {//mem si mem
                short addr2 = Integer.valueOf(i.getOptionalParam2()).shortValue();
                op2 = i.getFromMemory2().get(addr2);
            } else {//mem si reg
                int index = Integer.parseInt(i.getOptionalParam2(), 2);
                if (index >= 9 || index < 1) {
                    throw new InvalidInstructionFormatException("Register " + index + "out of bound");
                }
                short addr2 = LSUnit.loadFromRegister(index - 1);
                op2 = i.getFromMemory2().get(addr2);
            }
            if (i.getOp().equals("000011")) {//mov
                i.getFromMemory1().put(addr1, op2);
                StoreQueue.addInMemory(i);
            }
            AluResult aluResult = alu.compute(op1, op2, i.getOp());
            if (i.getOp().equals("000110")) {//cmp
                LSUnit.setFlags(aluResult.getFlags());
                return;
            }
            if (aluResult.getR1() != null) {//div, mul
                LSUnit.storeToRegister(0, aluResult.getR0());
                LSUnit.storeToRegister(1, aluResult.getR1());
                LSUnit.setZeroFlag(aluResult.getFlags());
                return;
            }
            i.getFromMemory1().put(addr1, aluResult.getResult());//add, sub
            LSUnit.setZeroFlag(aluResult.getFlags());
            StoreQueue.addInMemory(i);
        }
        if (src1.equals("10010")) {
            short addr1 = LSUnit.loadFromRegister(Integer.parseInt(i.getOptionalParam1(), 2) - 1);
            short op1 = i.getFromMemory1().get(addr1);
            short op2, addr2;
            if (src2.equals("10010")) {
                addr2 = LSUnit.loadFromRegister(Integer.parseInt(i.getOptionalParam2(), 2) - 1);
            } else {
                addr2 = Integer.valueOf(i.getOptionalParam2(), 2).shortValue();
            }
            op2 = i.getFromMemory2().get(addr2);
            if (i.getOp().equals("000011")) {
                i.getFromMemory1().put(addr1, op2);
                StoreQueue.addInMemory(i);
                return;
            }
            AluResult aluResult = alu.compute(op1, op2, i.getOp());
            if (i.getOp().equals("000110")) {
                LSUnit.setFlags(aluResult.getFlags());
                return;
            }
            if (aluResult.getR1() != null) {
                LSUnit.storeToRegister(0, aluResult.getR0());
                LSUnit.storeToRegister(1, aluResult.getR1());
                LSUnit.setZeroFlag(aluResult.getFlags());
                return;
            }
            i.getFromMemory1().put(addr1, aluResult.getResult());
            LSUnit.setZeroFlag(aluResult.getFlags());
            StoreQueue.addInMemory(i);
        }
    }


    private void executeMemImm(Instruction i) throws InvalidInstructionFormatException {//mem imm
        short addr1, op1, op2;
        if (i.getSrc1().equals("10001")) {
            addr1 = Integer.valueOf(i.getOptionalParam1(), 2).shortValue();
        } else {
            if (i.getSrc1().equals("10010")) {
                addr1 = LSUnit.loadFromRegister(Integer.parseInt(i.getOptionalParam1(), 2) - 1);
            } else {
                throw new InvalidInstructionFormatException("");
            }
        }
        op1 = i.getFromMemory1().get(addr1);
        op2 = Integer.valueOf(i.getOptionalParam2()).shortValue();
        if (i.getOp().equals("000011")) {//mov
            i.getFromMemory1().put(addr1, op2);
            StoreQueue.addInMemory(i);
            return;
        }
        AluResult aluResult = alu.compute(op1, op2, i.getOp());
        if (i.getOp().equals("000110")) {//cmp
            LSUnit.setFlags(aluResult.getFlags());
            return;
        }
        if (aluResult.getR1() != null) {//mul, div
            LSUnit.storeToRegister(0, aluResult.getR0());
            LSUnit.storeToRegister(1, aluResult.getR1());
            LSUnit.setZeroFlag(aluResult.getFlags());
            return;
        }
        i.getFromMemory1().put(addr1, aluResult.getResult());//add, sub
        LSUnit.setZeroFlag(aluResult.getFlags());
        StoreQueue.addInMemory(i);
    }

    private void executeMemReg(Instruction i) {// mem reg
        int index1, index2;
        short addr = Short.MAX_VALUE, op1, op2;
        index1 = Integer.parseInt(i.getSrc1(), 2) - 1;
        index2 = Integer.parseInt(i.getSrc2(), 2) - 1;
        if (index1 < 0 || index1 > 7) {//mem reg
            if (i.getSrc1().equals("10001")) {
                addr = Integer.valueOf(i.getSrc1(), 2).shortValue();
            } else {
                if (i.getSrc1().equals("10010")) {
                    addr = LSUnit.loadFromRegister(Integer.parseInt(i.getOptionalParam1(), 2) - 1);
                }
            }
            op1 = i.getFromMemory1().get(addr);
            op2 = LSUnit.loadFromRegister(index2);
            if (i.getOp().equals("000011")) {
                i.getFromMemory1().put(addr, op2);
                StoreQueue.addInMemory(i);
                return;
            }//mov
            AluResult aluResult = alu.compute(op1, op2, i.getOp());
            if (i.getOp().equals("000110")) {
                LSUnit.setFlags(aluResult.getFlags());
                return;
            }
            if (aluResult.getR1() != null) {
                LSUnit.storeToRegister(0, aluResult.getR0());
                LSUnit.storeToRegister(1, aluResult.getR1());
                LSUnit.setZeroFlag(aluResult.getFlags());
                return;
            }
            i.getFromMemory1().put(addr, aluResult.getResult());
            LSUnit.setZeroFlag(aluResult.getFlags());
            StoreQueue.addInMemory(i);
        } else {//reg mem
            if (i.getSrc2().equals("10001")) {
                addr = Integer.valueOf(i.getSrc2(), 2).shortValue();
            } else {
                if (i.getSrc1().equals("10010")) {
                    addr = LSUnit.loadFromRegister(Integer.parseInt(i.getOptionalParam2(), 2) - 1);
                }
            }
            op1 = LSUnit.loadFromRegister(index1);
            op2 = i.getFromMemory2().get(addr);
            if (i.getOp().equals("000011")) {
                LSUnit.storeToRegister(index1, op2);
                return;
            }//mov
            AluResult aluResult = alu.compute(op1, op2, i.getOp());
            if (i.getOp().equals("000110")) {
                LSUnit.setFlags(aluResult.getFlags());
                return;
            }
            if (aluResult.getR1() != null) {
                LSUnit.storeToRegister(0, aluResult.getR0());
                LSUnit.storeToRegister(1, aluResult.getR1());
                LSUnit.setZeroFlag(aluResult.getFlags());
                return;
            }
            LSUnit.storeToRegister(index1, aluResult.getResult());
            LSUnit.setZeroFlag(aluResult.getFlags());
        }
    }

    private void executeRegImm(Instruction i) {//reg imm
        int index = Integer.parseInt(i.getSrc1(), 2) - 1;
        short op1 = LSUnit.loadFromRegister(index);
        short op2 = Integer.valueOf(i.getOptionalParam2(), 2).shortValue();
        if (i.getOp().equals("000011")) {
            LSUnit.storeToRegister(index, op2);
            return;
        }
        AluResult aluResult = alu.compute(op1, op2, i.getOp());
        if (i.getOp().equals("000110")) {
            LSUnit.setFlags(aluResult.getFlags());
            return;
        }
        if (aluResult.getR1() != null) {
            LSUnit.storeToRegister(0, aluResult.getR0());
            LSUnit.storeToRegister(1, aluResult.getR1());
            LSUnit.setZeroFlag(aluResult.getFlags());
            return;
        }
        LSUnit.storeToRegister(index, aluResult.getResult());
        LSUnit.setZeroFlag(aluResult.getFlags());
    }

    private short findJumpAddress(Instruction i) throws InvalidInstructionFormatException {
        int index = Integer.parseInt(i.getSrc1(), 2) - 1;
        if (index >= 0 && index <= 7) {
            return LSUnit.loadFromRegister(index);
        }
        if (i.getSrc1().equals("10000")) {
            return Integer.valueOf(i.getOptionalParam1(), 2).shortValue();
        }
        if (i.getSrc1().equals("10001")) {
            short addr = Integer.valueOf(i.getOptionalParam1(), 2).shortValue();
            return i.getFromMemory1().get(addr);
        }
        if (i.getSrc1().equals("10010")) {
            int indexReg = Integer.parseInt(i.getOptionalParam1(), 2) - 1;
            if (!isRegister(indexReg)) {
                StageSignals.stopEverything();
                throw new InvalidInstructionFormatException("I cannot find the jump address.");
            }
            return i.getFromMemory1().get(LSUnit.loadFromRegister(indexReg));
        }
        throw new InvalidInstructionFormatException("I cannot find the jump address");
    }
    private void resetQueues(){
        ExecutionQueue.emptyQueue();
        InstructionQueue.emptyQueue();
        LoadQueue.emptyQueue();
    }
    private void executeJump(Instruction i) throws InvalidInstructionFormatException {
        if (!i.getSrc2().equals("00000")) {
            StageSignals.stopEverything();
            throw new InvalidInstructionFormatException("Jump must have only one destination!");
        }//0z 1e 2g
        short jumpAddr;
        switch (i.getOp()) {
            case "000111" -> {//jmp
                jumpAddr = findJumpAddress(i);
                LSUnit.setInstructionPointer(jumpAddr);
                StageSignals.executeJump();
                resetQueues();
            }
            case "001001" -> {//je
                if (LSUnit.getFlags()[1] == 1) {
                    jumpAddr = findJumpAddress(i);
                    LSUnit.setInstructionPointer(jumpAddr);
                    StageSignals.executeJump();
                    resetQueues();
                }
            }
            case "001010" -> {//jl
                if(LSUnit.getFlags()[1] == 0 && LSUnit.getFlags()[2] == 0){
                    jumpAddr = findJumpAddress(i);
                    LSUnit.setInstructionPointer(jumpAddr);
                    StageSignals.executeJump();
                    resetQueues();
                }
            }
            case "001011" -> {
                if(LSUnit.getFlags()[2]==1){
                    jumpAddr = findJumpAddress(i);
                    LSUnit.setInstructionPointer(jumpAddr);
                    StageSignals.executeJump();
                    resetQueues();
                }
            }
            case "001100" -> {
                if(LSUnit.getFlags()[0]==1){
                    jumpAddr = findJumpAddress(i);
                    LSUnit.setInstructionPointer(jumpAddr);
                    StageSignals.executeJump();
                    resetQueues();
                }
            }
            default -> {
                StageSignals.stopEverything();
                resetQueues();
                throw new InvalidInstructionFormatException("This is not a jump");
            }
        }
    }

    public void execute(Instruction instruction) throws InvalidInstructionFormatException {
        if (!instruction.isComplete()) {
            throw new InvalidInstructionFormatException("Incomplete instruction");//arunc exceptie daca instructiunea nu e completa
        }

        if (instruction.getSrc1().equals("100000")) {
            throw new InvalidInstructionFormatException("Destination must be a register or a memory address");
        }


        if (instruction.getOp().equals("001111")) {//end_sim
            StageSignals.stopEverything();
            ExecutionQueue.emptyQueue();
            LoadQueue.emptyQueue();
            InstructionQueue.emptyQueue();
            StoreQueue.emptyQueue();
        }
        if(jumps.contains(instruction.getOp())){//jumps
            executeJump(instruction);
            return;
        }

        boolean r1 = false, r2 = false;
        int reg1 = Integer.parseInt(instruction.getSrc1(), 2);
        int reg2 = Integer.parseInt(instruction.getSrc2(), 2);

        if (reg1 <= 8 && reg1 >= 1) {
            r1 = true;
        }

        if (reg2 <= 8 && reg2 >= 1) {
            r2 = true;
        }


        if (r1 && r2) {
            try {
                executeRegisterRegister(instruction);
                return;
            } catch (InvalidInstructionFormatException e) {
                StageSignals.stopEverything();
            }
        }


    }
}
