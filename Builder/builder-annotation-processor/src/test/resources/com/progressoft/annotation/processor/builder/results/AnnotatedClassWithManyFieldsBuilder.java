package com.progressoft.annotation.processor.builder;

class AnnotatedClassWithManyFieldsBuilder {

    private Integer integerValue;
    private int intValue;
    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;

    AnnotatedClassWithManyFields build() {
        return new AnnotatedClassWithManyFields(integerValue, intValue, stringValue, booleanValue, boolValue, doubleValue, dValue);
    }

    AnnotatedClassWithManyFieldsBuilder integerValue(Integer integerValue) {
        this.integerValue = integerValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder intValue(int intValue) {
        this.intValue = intValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder stringValue(String stringValue) {
        this.stringValue = stringValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder booleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder boolValue(boolean boolValue) {
        this.boolValue = boolValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder doubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder dValue(double dValue) {
        this.dValue = dValue;
        return this;
    }

}