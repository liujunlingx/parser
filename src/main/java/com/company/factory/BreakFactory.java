package com.company.factory;

import com.company.MiddleCode;
import com.company.resolver.ResolverContext;
import com.company.instruction.IExecutable;

import java.util.List;

/**
 * Created on 2018/9/26.
 */
public class BreakFactory implements IMiddleCodeFactory {
    @Override
    public MiddleCode build(ResolverContext context, String content, List<IExecutable> instructions) {
        return null;
    }
}
