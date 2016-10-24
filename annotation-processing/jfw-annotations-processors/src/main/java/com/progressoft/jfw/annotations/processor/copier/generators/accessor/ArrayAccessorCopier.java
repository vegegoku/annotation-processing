package com.progressoft.jfw.annotations.processor.copier.generators.accessor;

import com.progressoft.jfw.annotations.copier.DeepCopy;
import com.progressoft.jfw.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import com.progressoft.jfw.annotations.processor.utils.ProcessorElement;

import javax.lang.model.element.Element;
import java.util.Objects;

public class ArrayAccessorCopier implements FieldsCopyStatementGenerator {
    @Override
    public String generate(Element element) {
        return "\n        if(java.util.Objects.nonNull(original.get"+getCapitalizedFieldName(element)+"())){\n"+
            "                result.set"+getCapitalizedFieldName(element)+"(new "+new ProcessorElement(element).asSimpleType().replace("[]","")+"[original.get"+getCapitalizedFieldName(element)+"().length]);\n"+
                "                for(int i=0;i<original.get"+getCapitalizedFieldName(element)+"().length;i++){\n"+
                "                        result.get"+getCapitalizedFieldName(element)+"()[i]=original.get"+getCapitalizedFieldName(element)+"()[i]"+ (Objects.nonNull(element.getAnnotation(DeepCopy.class)) ? ".clone()" : "") +";\n"+
        "                }\n"+
        "        }\n";
    }
}
