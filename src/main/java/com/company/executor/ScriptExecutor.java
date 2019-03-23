package com.company.executor;

import com.company.instruction.IExecutable;

/**
 * Created on 2018/9/26.
 */
public class ScriptExecutor {

    private static ScriptExecutor instance = new ScriptExecutor();

    public static ScriptExecutor getInstance(){return instance;}

    public void execute(ScriptContext context){
        IExecutable instruction;
        Integer pc;
        while ((pc = context.getPc()) >= 0 && pc <= context.length() - 1){
            instruction = context.getInstruction(pc);
            int nextPc = instruction.execute(context);
            context.setPc(nextPc);
        }
    }

}
