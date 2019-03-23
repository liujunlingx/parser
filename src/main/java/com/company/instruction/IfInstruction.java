package com.company.instruction;

import com.company.executor.ScriptContext;
import lombok.Setter;

/**
 * Created on 2018/9/25.
 */
@Setter
public class IfInstruction implements IExecutable {

    private String expression;

    private Integer trueGoTo;

    private Integer falseGoTo;

    @Override
    public int execute(ScriptContext scriptContext) {
        String result = scriptContext.evaluate(scriptContext.getVelocityContext(),expression);
        return Boolean.TRUE.toString().equalsIgnoreCase(result) ? trueGoTo : falseGoTo;
    }

}
