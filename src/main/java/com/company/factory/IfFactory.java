package com.company.factory;

import com.company.MiddleCode;
import com.company.annotation.KeyWord;
import com.company.enums.MiddleCodeType;
import com.company.resolver.ResolverContext;
import com.company.instruction.IExecutable;
import com.company.instruction.IfInstruction;

import java.util.List;

/**
 * Created on 2018/9/26.
 */
@KeyWord("#if")
public class IfFactory implements IMiddleCodeFactory {
    @Override
    public MiddleCode build(ResolverContext context, String content, List<IExecutable> instructions) {
        String expression = content + "\ntrue#else\nfalse#end";

        IfInstruction ifInstruction = new IfInstruction();
        ifInstruction.setExpression(expression);
        ifInstruction.setTrueGoTo(instructions.size() + 1);
        instructions.add(ifInstruction);

        MiddleCode<IfInstruction> middleCode = new MiddleCode<>(ifInstruction,MiddleCodeType.IF,instructions.size() - 1,instructions.size());

        context.getIfSegmentStack().addFirst(middleCode);
        return middleCode;
    }
}
