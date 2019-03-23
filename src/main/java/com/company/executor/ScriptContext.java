package com.company.executor;

import com.company.instruction.IExecutable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.List;

/**
 * Created on 2018/9/25.
 */
@Getter
@Setter
@Builder
public class ScriptContext {

    /**
     * 脚本解析后生成的所有指令
     */
    private List<IExecutable> instructions;

    /**
     * 当前正在执行的segment的序号，从0开始
     */
    private Integer pc;

    /**
     * template engine
     */
    private VelocityContext velocityContext;

    IExecutable getInstruction(int index){
        if(index < 0 || index > length() - 1){
            return null;
        }
        return this.instructions.get(index);
    }

    Integer length(){
        return this.instructions.size();
    }

    public String evaluate(VelocityContext context, String template){
        if(StringUtils.isBlank(template)){
            return template;
        }
        StringWriter out = new StringWriter();
        Velocity.evaluate(context,out,"Evaluate Error",template);
        return out.toString();
    }
}
