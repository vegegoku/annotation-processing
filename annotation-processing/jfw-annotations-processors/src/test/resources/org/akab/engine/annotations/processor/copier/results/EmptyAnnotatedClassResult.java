package org.akab.engine.annotations.processor.copier;

class EmptyAnnotatedClassCopier {

    EmptyAnnotatedClass copy(EmptyAnnotatedClass original) throws CloneNotSupportedException {
        EmptyAnnotatedClass result=new EmptyAnnotatedClass();
        return result;
    }
}