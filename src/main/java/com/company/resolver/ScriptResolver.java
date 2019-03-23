package com.company.resolver;

import com.company.MiddleCode;
import com.company.annotation.KeyWord;
import com.company.executor.ScriptContext;
import com.company.factory.CommonFactory;
import com.company.factory.IMiddleCodeFactory;
import com.company.instruction.IExecutable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.reflections.Reflections;

import java.io.*;
import java.util.*;

/**
 * Created on 2018/9/26.
 */
@Setter
@Getter
@Log
public class ScriptResolver {

    private Map<String,IMiddleCodeFactory> cache = new HashMap<>();

    private IMiddleCodeFactory DEFAULT = new CommonFactory();

    public ScriptResolver(){
        //∑¥…‰ππ‘Ïcache
        Reflections reflections = new Reflections("com.company");
        Set<Class<?>> keywords = reflections.getTypesAnnotatedWith(KeyWord.class);
        try {
            for(Class<?> clazz : keywords){
                KeyWord keyword = clazz.getAnnotation(KeyWord.class);
                cache.put(keyword.value()[0], (IMiddleCodeFactory) clazz.newInstance());
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private ScriptContext scriptContext;

    public ScriptContext parse(String fileName){
        ScriptContext context = ScriptContext.builder()
                .instructions(parseFile(fileName))
                .pc(0)
                .velocityContext(new VelocityContext())
                .build();

        return context;
    }

    private List<IExecutable> parseFile(String fileName){
        List<IExecutable> result = new LinkedList<>();

        List<String> lines = readFile(fileName);
        ResolverContext resolverContext = new ResolverContext();
        MiddleCode toFill;
        for (int i = 0; i < lines.size(); i++) {
            if(StringUtils.isBlank(lines.get(i))){
                continue;
            }
            String line = lines.get(i);
            IMiddleCodeFactory middleCodeFactory = null;

            for(Map.Entry<String,IMiddleCodeFactory> entry : cache.entrySet()){
                if(line.startsWith(entry.getKey())){
                    middleCodeFactory = entry.getValue();
                    break;
                }
            }
            if(middleCodeFactory == null){
                middleCodeFactory = DEFAULT;
            }
            toFill = middleCodeFactory.build(resolverContext,line,result);
        }
        return result;
    }

    public List<String> readFile(String fileName){
        String content = null;
        ClassLoader classLoader = getClass().getClassLoader();
        try(InputStream in = classLoader.getResourceAsStream(fileName)) {
            content = IOUtils.toString(in);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        List<String> result = new ArrayList<>();
        for(String line : content.split(System.lineSeparator())){
            result.add(line.trim());
        }
        return result;
    }

}
