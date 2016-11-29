package org.akab.engine.annotations.processor.builder;

public class InnerClassBuilder {

    public InnerClassBuilder(){

    }

    public AnnotatedClassWithInnerClass.InnerClass build(){
        return new AnnotatedClassWithInnerClass.InnerClass();
    }
}
