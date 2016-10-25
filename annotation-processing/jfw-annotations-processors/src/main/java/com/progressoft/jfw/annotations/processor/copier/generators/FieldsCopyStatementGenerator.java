package com.progressoft.jfw.annotations.processor.copier.generators;


import com.progressoft.jfw.annotations.processor.utils.ProcessorElement;
import org.apache.commons.lang.WordUtils;

import javax.lang.model.element.Element;

public interface FieldsCopyStatementGenerator {

    String COPIER_POSTFIX = "Copier";
    String ORIGINAL = "original";
    String RESULT = "result";

    String generate(ProcessorElement processorElement);
    default String getCapitalizedFieldName(ProcessorElement processorElement){
        return WordUtils.capitalize(processorElement.simpleName());
    }

    default String getGenericType(ProcessorElement processorElement){
        String simpleType=processorElement.asSimpleType();
        return simpleType.substring(simpleType.indexOf("<")+1, simpleType.lastIndexOf(">"));
    }
}
