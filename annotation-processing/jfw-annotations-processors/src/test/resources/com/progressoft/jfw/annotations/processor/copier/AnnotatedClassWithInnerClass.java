package com.progressoft.jfw.annotations.processor.copier;

import com.progressoft.jfw.annotations.copier.WithCopier;

@WithCopier
public class AnnotatedClassWithInnerClass {

    @WithCopier
    public static class InnerClass{

    }
}
