package com.progressoft.annotation.processor.builder;

import java.lang.Integer;

@WithBuilder
public class AnnotatedClassWithSingleProperty {

    private Integer integerValue;

    public AnnotatedClassWithSingleProperty(Integer integerValue) {
        this.integerValue = integerValue;
    }
}
