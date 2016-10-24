package com.progressoft.jfw.annotations.processor.test;

import javax.annotation.processing.Processor;


public class InputSource {

    private String inputClassName;

    public InputSource(String inputClassName) {
        this.inputClassName = inputClassName;
    }

    public TargetProcessor withProcessor(Processor processor){
        return new TargetProcessor(inputClassName, processor);
    }


}
