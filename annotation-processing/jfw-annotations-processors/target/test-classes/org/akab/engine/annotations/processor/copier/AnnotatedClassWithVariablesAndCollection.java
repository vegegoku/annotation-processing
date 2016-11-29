package org.akab.engine.annotations.processor.copier;

import java.util.List;

import org.akab.engine.annotations.copier.CollectionCopy;
import org.akab.engine.annotations.copier.DeepCopy;
import org.akab.engine.annotations.copier.WithCopier;
import org.akab.engine.annotations.processor.copier.AnnotatedClassWithVariablesAndCollectionCopier;

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
