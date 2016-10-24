package com.progressoft.jfw.annotations.processor.copier.generators;


import com.progressoft.jfw.annotations.processor.utils.ProcessorElement;
import org.apache.commons.lang.WordUtils;

import javax.lang.model.element.Element;

public interface FieldsCopyStatementGenerator {

    final String COPIER_POSTFIX = "Copier";
    final String ORIGINAL = "original";
    final String RESULT = "result";

    String generate(Element element);
    default String getCapitalizedFieldName(Element element){
        return WordUtils.capitalize(element.getSimpleName().toString());
    }

    default String getGenericType(Element element){
        String simpleType=new ProcessorElement(element).asSimpleType();
        return simpleType.substring(simpleType.indexOf("<")+1, simpleType.lastIndexOf(">"));
    }
}
