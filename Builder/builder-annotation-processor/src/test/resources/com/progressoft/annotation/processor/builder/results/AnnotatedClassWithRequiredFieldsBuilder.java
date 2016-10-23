package com.progressoft.annotation.processor.builder;

import java.util.List;

public class AnnotatedClassWithRequiredFieldsBuilder {

    private final Integer integerValue;
    private final int intValue;
    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;
    private List<String> stringsList;

    public AnnotatedClassWithRequiredFieldsBuilder(Integer integerValue, int intValue){
        this.integerValue = integerValue;
        this.intValue=intValue;
    }

    public AnnotatedClassWithRequiredFields build() {
        return new AnnotatedClassWithRequiredFields(integerValue, intValue, stringValue, booleanValue, boolValue, doubleValue, dValue, stringsList);
    }

    public AnnotatedClassWithRequiredFieldsBuilder stringValue(String stringValue) {
        this.stringValue = stringValue;
        return this;
    }

    public AnnotatedClassWithRequiredFieldsBuilder booleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
        return this;
    }

    public AnnotatedClassWithRequiredFieldsBuilder boolValue(boolean boolValue) {
        this.boolValue = boolValue;
        return this;
    }

    public AnnotatedClassWithRequiredFieldsBuilder doubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
        return this;
    }

    public AnnotatedClassWithRequiredFieldsBuilder dValue(double dValue) {
        this.dValue = dValue;
        return this;
    }

    public AnnotatedClassWithRequiredFieldsBuilder stringsList(List<String> stringsList) {
        this.stringsList = stringsList;
        return this;
    }
}