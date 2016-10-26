package com.progressoft.jfw.annotations.processor.copier;

import com.progressoft.jfw.annotations.copier.*;

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
