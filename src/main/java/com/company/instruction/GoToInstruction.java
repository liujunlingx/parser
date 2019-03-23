package com.company.instruction;

import com.company.executor.ScriptContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on 2018/9/25.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GoToInstruction implements IExecutable {

    private Integer goTo;

    @Override
    public int execute(ScriptContext scriptContext) {
        return goTo;
    }
}
