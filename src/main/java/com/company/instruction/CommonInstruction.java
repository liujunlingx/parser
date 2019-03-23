package com.company.instruction;

import com.company.executor.ScriptContext;
import lombok.AllArgsConstructor;

/**
 * Created on 2018/9/25.
 */
@AllArgsConstructor
public class CommonInstruction implements IExecutable {

    private String content;

    @Override
    public int execute(ScriptContext scriptContext) {
        System.out.println(String.format("execute the %d th instruction : %s",scriptContext.getPc(),content));
        return scriptContext.getPc() + 1;
    }
}
