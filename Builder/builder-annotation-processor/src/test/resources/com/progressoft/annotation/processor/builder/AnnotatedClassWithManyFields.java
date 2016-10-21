package com.progressoft.annotation.processor.builder;

@WithBuilder
public class AnnotatedClassWithManyFields {

    private Integer integerValue;
    private int intValue;
    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;

    public AnnotatedClassWithManyFields(Integer integerValue, int intValue, String stringValue, Boolean booleanValue, boolean boolValue, Double doubleValue, double dValue) {
        this.integerValue = integerValue;
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.booleanValue = booleanValue;
        this.boolValue = boolValue;
        this.doubleValue = doubleValue;
        this.dValue = dValue;
    }
}
