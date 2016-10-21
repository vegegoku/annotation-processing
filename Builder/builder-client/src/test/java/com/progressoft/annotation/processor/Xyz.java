package com.progressoft.annotation.processor;

import com.progressoft.annotation.processor.builder.BuilderRequired;
import com.progressoft.annotation.processor.builder.WithBuilder;

import java.util.List;

@WithBuilder
public class Xyz {

    @BuilderRequired
    private Integer integerValue;

    @BuilderRequired
    private int intValue;

    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;
    private List<String> stringsList;

    public Xyz(Integer integerValue, int intValue, String stringValue, Boolean booleanValue, boolean boolValue, Double doubleValue, double dValue, List<String> stringsList) {
        this.integerValue = integerValue;
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.booleanValue = booleanValue;
        this.boolValue = boolValue;
        this.doubleValue = doubleValue;
        this.dValue = dValue;
        this.stringsList = stringsList;
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
