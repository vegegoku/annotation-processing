package org.akab.engine.annotations.processor.copier;

class AnnotatedClassWithStaticFieldsCopier {

    AnnotatedClassWithStaticFields copy(AnnotatedClassWithStaticFields original) throws CloneNotSupportedException {
        AnnotatedClassWithStaticFields result=new AnnotatedClassWithStaticFields();

        result.setIntegerValue(original.getIntegerValue());
        result.setDoubelValue(original.getDoubelValue());

        return result;
    }
}