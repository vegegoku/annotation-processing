package org.akab.engine.annotations.processor.builder;

import java.lang.Integer;

import org.akab.engine.annotations.builder.WithBuilder;

@WithBuilder
public class AnnotatedClassWithSingleProperty {

    private Integer integerValue;

    public AnnotatedClassWithSingleProperty(Integer integerValue) {
        this.integerValue = integerValue;
    }
}
