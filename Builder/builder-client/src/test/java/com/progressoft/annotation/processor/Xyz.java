package com.progressoft.annotation.processor;

import com.progressoft.annotation.processor.builder.WithBuilder;

@WithBuilder
public class Xyz {

    private Integer integerValue;
    private int intValue;
    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;
    private Abc<String> abc;

    public Xyz(Integer integerValue, int intValue, String stringValue, Boolean booleanValue, boolean boolValue, Double doubleValue, double dValue, Abc<String> abc) {
        this.integerValue = integerValue;
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.booleanValue = booleanValue;
        this.boolValue = boolValue;
        this.doubleValue = doubleValue;
        this.dValue = dValue;
        this.abc=abc;
    }

    @Override
    public String toString() {
        return "Xyz{" +
                "integerValue=" + integerValue +
                ", intValue=" + intValue +
                ", stringValue='" + stringValue + '\'' +
                ", booleanValue=" + booleanValue +
                ", boolValue=" + boolValue +
                ", doubleValue=" + doubleValue +
                ", dValue=" + dValue +
                '}';
    }
}
