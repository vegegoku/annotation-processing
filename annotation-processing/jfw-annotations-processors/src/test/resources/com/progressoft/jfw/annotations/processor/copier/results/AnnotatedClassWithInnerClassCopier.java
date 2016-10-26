package com.progressoft.jfw.annotations.processor.copier;

class AnnotatedClassWithInnerClassCopier {

    AnnotatedClassWithInnerClass copy(AnnotatedClassWithInnerClass original) throws CloneNotSupportedException {
        AnnotatedClassWithInnerClass result = new AnnotatedClassWithInnerClass();

        return result;
    }
}
