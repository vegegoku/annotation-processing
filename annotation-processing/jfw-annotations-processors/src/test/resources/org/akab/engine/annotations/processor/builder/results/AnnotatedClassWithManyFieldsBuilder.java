package org.akab.engine.annotations.processor.builder;

import java.util.List;

public class AnnotatedClassWithManyFieldsBuilder {

    private Integer integerValue;
    private int intValue;
    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;
    private List<String> stringsList;

    public AnnotatedClassWithManyFieldsBuilder(){

    }

    public AnnotatedClassWithManyFields build() {
        return new AnnotatedClassWithManyFields(integerValue, intValue, stringValue, booleanValue, boolValue, doubleValue, dValue, stringsList);
    }

    public AnnotatedClassWithManyFieldsBuilder integerValue(Integer integerValue) {
        this.integerValue = integerValue;
        return this;
    }

    public AnnotatedClassWithManyFieldsBuilder intValue(int intValue) {
        this.intValue = intValue;
        return this;
    }

    public AnnotatedClassWithManyFieldsBuilder stringValue(String stringValue) {
        this.stringValue = stringValue;
        return this;
    }

    public AnnotatedClassWithManyFieldsBuilder booleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
        return this;
    }

    public AnnotatedClassWithManyFieldsBuilder boolValue(boolean boolValue) {
        this.boolValue = boolValue;
        return this;
    }

    public AnnotatedClassWithManyFieldsBuilder doubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
        return this;
    }

    public AnnotatedClassWithManyFieldsBuilder dValue(double dValue) {
        this.dValue = dValue;
        return this;
    }

    public AnnotatedClassWithManyFieldsBuilder stringsList(List<String> stringsList) {
        this.stringsList = stringsList;
        return this;
    }

}