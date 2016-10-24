package com.progressoft.jfw.annotations.processor.test;


public class ProcessorAssert {
    public static InputSource assertProcessing(String inputClassName){
        return new InputSource(inputClassName);
    }
}
