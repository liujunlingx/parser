package com.company.instruction;

import com.company.executor.ScriptContext;

/**
 * Created on 2018/9/25.
 */
public interface IExecutable {

    /**
     * 执行该指令，返回下一条指令的index
     * @param scriptContext
     * @return 下一条待执行指令的index
     */
    int execute(ScriptContext scriptContext);
}
