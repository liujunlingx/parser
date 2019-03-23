package com.company.factory;

import com.company.MiddleCode;
import com.company.instruction.IExecutable;
import com.company.resolver.ResolverContext;

import java.util.List;

/**
 * Created on 2018/9/26.
 */
public class ContinueFactory implements IMiddleCodeFactory {
    @Override
    public MiddleCode build(ResolverContext context, String content, List<IExecutable> instructions) {
        return null;
    }
}
