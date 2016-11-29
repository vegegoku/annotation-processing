package org.akab.engine.annotations.processor.builder;

import java.util.List;

public class AnnotatedClassWithStaticFieldsBuilder {

    private int intValue;
    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;
    private List<String> stringsList;

    public AnnotatedClassWithStaticFieldsBuilder(){

    }

    public AnnotatedClassWithStaticFields build() {
        return new AnnotatedClassWithStaticFields(intValue, stringValue, booleanValue, boolValue, doubleValue, dValue, stringsList);
    }

    public AnnotatedClassWithStaticFieldsBuilder intValue(int intValue) {
        this.intValue = intValue;
        return this;
    }

    public AnnotatedClassWithStaticFieldsBuilder stringValue(String stringValue) {
        this.stringValue = stringValue;
        return this;
    }

    public AnnotatedClassWithStaticFieldsBuilder booleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
        return this;
    }

    public AnnotatedClassWithStaticFieldsBuilder boolValue(boolean boolValue) {
        this.boolValue = boolValue;
        return this;
    }

    public AnnotatedClassWithStaticFieldsBuilder doubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
        return this;
    }

    public AnnotatedClassWithStaticFieldsBuilder dValue(double dValue) {
        this.dValue = dValue;
        return this;
    }

    public AnnotatedClassWithStaticFieldsBuilder stringsList(List<String> stringsList) {
        this.stringsList = stringsList;
        return this;
    }

}