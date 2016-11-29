package org.akab.engine.annotations.processor.builder;

import org.akab.engine.annotations.builder.WithBuilder;

@WithBuilder
public class AnnotatedClassWithInnerClass {

    @WithBuilder
    public static class InnerClass{

    }
}
