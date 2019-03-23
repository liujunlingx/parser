package com.company.factory;

import com.company.MiddleCode;
import com.company.annotation.KeyWord;
import com.company.enums.MiddleCodeType;
import com.company.instruction.IfInstruction;
import com.company.resolver.ResolverContext;
import com.company.instruction.IExecutable;

import java.util.List;

/**
 * Created on 2018/9/26.
 */
@KeyWord("#else")
public class ElseFactory implements IMiddleCodeFactory {
    @Override
    public MiddleCode build(ResolverContext context, String content, List<IExecutable> instructions) {
        MiddleCode mostRecentIf = context.getIfSegmentStack().removeFirst();

        String expression = mostRecentIf.getExtraInfo()[0];
        String template = expression + "\nfalse#else\ntrue#end";

        IfInstruction ifInstruction = new IfInstruction();
        ifInstruction.setExpression(template);
        ifInstruction.setTrueGoTo(instructions.size() + 1);
        instructions.add(ifInstruction);

        //回填最近一个if语句的falseGoTo
        IfInstruction instruction = (IfInstruction) mostRecentIf.getInstruction();
        instruction.setFalseGoTo(instructions.size() - 1);

        MiddleCode<IfInstruction> middleCode = new MiddleCode<>(ifInstruction,MiddleCodeType.IF,
                instructions.size() - 1,instructions.size());
        context.getIfSegmentStack().addFirst(middleCode);
        return middleCode;
    }
}
