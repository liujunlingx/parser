package com.company.factory;

import com.company.MiddleCode;
import com.company.annotation.KeyWord;
import com.company.instruction.GoToInstruction;
import com.company.instruction.IfInstruction;
import com.company.resolver.ResolverContext;
import com.company.instruction.IExecutable;

import java.util.List;

/**
 * Created on 2018/9/26.
 */
@KeyWord("#end")
public class EndFactory implements IMiddleCodeFactory {
    @Override
    public MiddleCode build(ResolverContext context, String content, List<IExecutable> instructions) {
        MiddleCode middleCode = context.getIfSegmentStack().removeFirst();
        IfInstruction ifInstruction = (IfInstruction) middleCode.getInstruction();
        ifInstruction.setFalseGoTo(instructions.size());
        middleCode.setExpectIndex(instructions.size());

        //回填所有#if、#elif的body中最后接的goto
        while (!context.getGoToInstructions().isEmpty()){
            GoToInstruction go = context.getGoToInstructions().removeFirst();
            go.setGoTo(instructions.size());
        }
        return middleCode;
    }
}
