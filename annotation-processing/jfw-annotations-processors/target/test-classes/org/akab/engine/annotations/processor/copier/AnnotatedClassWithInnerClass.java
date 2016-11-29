package org.akab.engine.annotations.processor.copier;

import org.akab.engine.annotations.copier.WithCopier;

@WithCopier
public class AnnotatedClassWithInnerClass {

    @WithCopier
    public static class InnerClass{

    }
}
