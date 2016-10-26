package com.progressoft.jfw.annotations.processor.builder;

import com.progressoft.jfw.annotations.builder.WithBuilder;

@WithBuilder
public class AnnotatedClassWithInnerClass {

    @WithBuilder
    public static class InnerClass{

    }
}
