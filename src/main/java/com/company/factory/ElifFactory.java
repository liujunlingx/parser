package com.company.factory;

import com.company.MiddleCode;
import com.company.annotation.KeyWord;
import com.company.enums.MiddleCodeType;
import com.company.instruction.GoToInstruction;
import com.company.instruction.IfInstruction;
import com.company.resolver.ResolverContext;
import com.company.instruction.IExecutable;

import java.util.List;

/**
 * Created on 2018/9/26.
 */
@KeyWord("#elif")
public class ElifFactory implements IMiddleCodeFactory {
    @Override
    public MiddleCode build(ResolverContext context, String content, List<IExecutable> instructions) {
        String expression = "#if" + content.substring("#elif".length());
        String template =  expression + "\ntrue#else\nfalse#end";

        GoToInstruction go = new GoToInstruction();
        instructions.add(go);
        context.getGoToInstructions().add(go);

        IfInstruction ifInstruction = new IfInstruction();
        ifInstruction.setExpression(template);
        ifInstruction.setTrueGoTo(instructions.size() + 1);
        instructions.add(ifInstruction);

        //回填最近一个if语句的falseGoTo
        MiddleCode mostRecentIf = context.getIfSegmentStack().removeFirst();
        IfInstruction instruction = (IfInstruction) mostRecentIf.getInstruction();
        instruction.setFalseGoTo(instructions.size() - 1);

        MiddleCode<IfInstruction> middleCode = new MiddleCode<>(ifInstruction,MiddleCodeType.IF,
                instructions.size()-1,instructions.size(),expression);
        context.getIfSegmentStack().add(middleCode);
        return middleCode;
    }
}
