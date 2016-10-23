package com.progressoft.annotation.processor.builder;


public class AnnotatedClassWithSinglePropertyBuilder {

    private Integer integerValue;

    public AnnotatedClassWithSinglePropertyBuilder(){

    }

    public AnnotatedClassWithSingleProperty build(){
        return new AnnotatedClassWithSingleProperty(integerValue);
    }

    public AnnotatedClassWithSinglePropertyBuilder integerValue(Integer integerValue){
        this.integerValue=integerValue;
        return this;
    }
}
