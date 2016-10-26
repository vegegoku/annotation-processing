package com.progressoft.jfw.annotations.processor.copier;

import java.util.List;

import com.progressoft.jfw.annotations.copier.*;

@WithCopier(mode = WithCopier.Mode.PUBLIC_MEMEBERS)
public class AnnotatedClassWithVariablesAndCollection implements  Cloneable{

    public int intValeu;
    public String strValue;

    @CollectionCopy(initializer = "new java.util.ArrayList<>()")
    @DeepCopy
    public List<AnnotatedClassWithVariablesAndCollection> samples;

    @Override
    protected AnnotatedClassWithVariablesAndCollection clone() throws CloneNotSupportedException {
        return new AnnotatedClassWithVariablesAndCollectionCopier().copy(this);
    }
}
