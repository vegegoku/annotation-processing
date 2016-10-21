package com.progressoft.annotation.processor.builder;

import java.util.List;

class AnnotatedClassWithRequiredFieldsBuilder {

    private final Integer integerValue;
    private final int intValue;
    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;
    private List<String> stringsList;

    AnnotatedClassWithRequiredFieldsBuilder(Integer integerValue, int intValue){
        this.integerValue = integerValue;
        this.intValue=intValue;
    }

    AnnotatedClassWithRequiredFields build() {
        return new AnnotatedClassWithRequiredFields(integerValue, intValue, stringValue, booleanValue, boolValue, doubleValue, dValue, stringsList);
    }

    AnnotatedClassWithRequiredFieldsBuilder stringValue(String stringValue) {
        this.stringValue = stringValue;
        return this;
    }

    AnnotatedClassWithRequiredFieldsBuilder booleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
        return this;
    }

    AnnotatedClassWithRequiredFieldsBuilder boolValue(boolean boolValue) {
        this.boolValue = boolValue;
        return this;
    }

    AnnotatedClassWithRequiredFieldsBuilder doubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
        return this;
    }

    AnnotatedClassWithRequiredFieldsBuilder dValue(double dValue) {
        this.dValue = dValue;
        return this;
    }

    AnnotatedClassWithRequiredFieldsBuilder stringsList(List<String> stringsList) {
        this.stringsList = stringsList;
        return this;
    }
}