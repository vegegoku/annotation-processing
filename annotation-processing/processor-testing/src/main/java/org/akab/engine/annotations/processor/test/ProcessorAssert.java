package org.akab.engine.annotations.processor.test;


public class ProcessorAssert {
    public static InputSource assertProcessing(String inputClassName){
        return new InputSource(inputClassName);
    }
}
