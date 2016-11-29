package org.akab.engine.annotations.processor.copier;

import org.akab.engine.annotations.copier.WithCopier;

@WithCopier
public class AnnotatedClassWithSingleField {

    private int integerField;

    public int getIntegerField() {
        return integerField;
    }

    public void setIntegerField(int integerField) {
        this.integerField = integerField;
    }
}
