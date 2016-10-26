package com.progressoft.jfw.annotations.processor.builder;

public class InnerClassBuilder {

    public InnerClassBuilder(){

    }

    public AnnotatedClassWithInnerClass.InnerClass build(){
        return new AnnotatedClassWithInnerClass.InnerClass();
    }
}
