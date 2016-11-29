package org.akab.engine.annotations.processor.builder;

import java.util.List;
import java.util.logging.Logger;

import org.akab.engine.annotations.builder.WithBuilder;

@WithBuilder
public class AnnotatedClassWithStaticFields {

    private static final Logger logger=Logger.getLogger(AnnotatedClassWithStaticFields.class.getName());

    private static Integer integerValue=0;
    private int intValue;
    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;
    private List<String> stringsList;

    public AnnotatedClassWithStaticFields(int intValue, String stringValue, Boolean booleanValue, boolean boolValue, Double doubleValue, double dValue, List<String> stringsList) {
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.booleanValue = booleanValue;
        this.boolValue = boolValue;
        this.doubleValue = doubleValue;
        this.dValue = dValue;
        this.stringsList=stringsList;
    }
}
