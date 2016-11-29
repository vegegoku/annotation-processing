package org.akab.engine.annotations.processor.copier;

class SecondEmptyAnnotatedClassCopier {

    SecondEmptyAnnotatedClass copy(SecondEmptyAnnotatedClass original) throws CloneNotSupportedException {
        SecondEmptyAnnotatedClass result=new SecondEmptyAnnotatedClass();
        return result;
    }
}