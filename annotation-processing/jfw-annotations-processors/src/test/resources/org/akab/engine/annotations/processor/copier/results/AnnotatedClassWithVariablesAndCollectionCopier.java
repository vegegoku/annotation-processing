package org.akab.engine.annotations.processor.copier;

import java.util.List;

import org.akab.engine.annotations.processor.copier.AnnotatedClassWithVariablesAndCollection;

class AnnotatedClassWithVariablesAndCollectionCopier{

    AnnotatedClassWithVariablesAndCollection copy(AnnotatedClassWithVariablesAndCollection original) throws CloneNotSupportedException{
        AnnotatedClassWithVariablesAndCollection result=new AnnotatedClassWithVariablesAndCollection();

        result.intValeu=original.intValeu;
        result.strValue=original.strValue;

        if(java.util.Objects.nonNull(original.samples)){
            result.samples=new java.util.ArrayList<>();
            for (AnnotatedClassWithVariablesAndCollection item :original.samples) {
                result.samples.add(item.clone());
            }
        }

        return result;
    }

}
