package com.company;

import com.company.enums.MiddleCodeType;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;

/**
 * Created on 2018/9/25.
 */
@Getter
@Setter
public class MiddleCode<T> {

    private T instruction;

    private MiddleCodeType type;

    private Integer index;

    private Integer expectIndex;

    private String[] extraInfo;

    public MiddleCode(T instruction,MiddleCodeType type,Integer index,Integer expectIndex,String... extraInfo){
        this.instruction = instruction;
        this.type = type;
        this.index = index;
        this.expectIndex = expectIndex;
        this.extraInfo = extraInfo;
    }

    public static void main(String[] args) {
        if(1 == 1){
            System.out.println("A");
        }else if (2 == 2){
            System.out.println("B");
        }else {
            System.out.println("C");
        }
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());
    }
}
