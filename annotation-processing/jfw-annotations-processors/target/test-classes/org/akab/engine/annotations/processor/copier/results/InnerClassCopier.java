package org.akab.engine.annotations.processor.copier;

class InnerClassCopier {

    AnnotatedClassWithInnerClass.InnerClass copy(AnnotatedClassWithInnerClass.InnerClass original) throws CloneNotSupportedException {
        AnnotatedClassWithInnerClass.InnerClass result= new AnnotatedClassWithInnerClass.InnerClass();

        return result;
    }
}
