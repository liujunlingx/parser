package com.company.instruction;

import com.company.executor.ScriptContext;

/**
 * Created on 2018/9/25.
 */
public interface IExecutable {

    /**
     * ִ�и�ָ�������һ��ָ���index
     * @param scriptContext
     * @return ��һ����ִ��ָ���index
     */
    int execute(ScriptContext scriptContext);
}
