package com.progressoft.jfw.annotations.processor.builder;

import java.lang.Integer;

import com.progressoft.jfw.annotations.builder.*;

@WithBuilder
public class AnnotatedClassWithSingleProperty {

    private Integer integerValue;

    public AnnotatedClassWithSingleProperty(Integer integerValue) {
        this.integerValue = integerValue;
    }
}
