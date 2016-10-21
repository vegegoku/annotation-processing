package com.progressoft.annotation.processor;

import com.progressoft.annotation.processor.Abc;

class XyzBuilder {

    private Integer integerValue;
    private int intValue;
    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;
    private Abc<String> abc;

    Xyz build(){
        return new Xyz(integerValue,intValue,stringValue,booleanValue,boolValue,doubleValue,dValue,abc);
    }

    XyzBuilder integerValue(Integer integerValue){
        this.integerValue = integerValue;
        return this;
    }

    XyzBuilder intValue(int intValue){
        this.intValue = intValue;
        return this;
    }

    XyzBuilder stringValue(String stringValue){
        this.stringValue = stringValue;
        return this;
    }

    XyzBuilder booleanValue(Boolean booleanValue){
        this.booleanValue = booleanValue;
        return this;
    }

    XyzBuilder boolValue(boolean boolValue){
        this.boolValue = boolValue;
        return this;
    }

    XyzBuilder doubleValue(Double doubleValue){
        this.doubleValue = doubleValue;
        return this;
    }

    XyzBuilder dValue(double dValue){
        this.dValue = dValue;
        return this;
    }

    XyzBuilder abc(Abc<String> abc){
        this.abc = abc;
        return this;
    }
}