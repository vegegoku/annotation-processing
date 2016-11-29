package org.akab.engine.annotations.processor.copier;

import org.akab.engine.annotations.copier.WithCopier;

import java.util.logging.Logger;

@WithCopier
public class AnnotatedClassWithStaticFields {

    private static final Logger logger=Logger.getLogger(AnnotatedClassWithStaticFields.class.getName());

    private static String stringValue;
    private int integerValue;
    private Double doubelValue;

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(int integerValue) {
        this.integerValue = integerValue;
    }

    public Double getDoubelValue() {
        return doubelValue;
    }

    public void setDoubelValue(Double doubelValue) {
        this.doubelValue = doubelValue;
    }
}
