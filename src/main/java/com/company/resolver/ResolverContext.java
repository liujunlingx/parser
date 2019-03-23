package com.company.resolver;

import com.company.MiddleCode;
import com.company.instruction.GoToInstruction;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018/9/26.
 */
@Getter
public class ResolverContext {

    private LinkedList<MiddleCode> ifSegmentStack = new LinkedList<>();

    private LinkedList<GoToInstruction> goToInstructions = new LinkedList<>();

    private LinkedList<MiddleCode> forSegmentStack = new LinkedList<>();

    private Map<MiddleCode,List<GoToInstruction>> continueMap = new HashMap<>();

    private Map<MiddleCode,List<GoToInstruction>> breakMap = new HashMap<>();
}
