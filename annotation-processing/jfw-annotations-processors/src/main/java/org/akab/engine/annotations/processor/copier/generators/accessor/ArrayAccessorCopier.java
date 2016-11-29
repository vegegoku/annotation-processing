package org.akab.engine.annotations.processor.copier.generators.accessor;

import org.akab.engine.annotations.copier.DeepCopy;
import org.akab.engine.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import org.akab.engine.annotations.processor.utils.ProcessorElement;

import java.util.Objects;

public class ArrayAccessorCopier implements FieldsCopyStatementGenerator {
    @Override
    public String generate(ProcessorElement element) {
        return "\n        if(java.util.Objects.nonNull(original.get"+getCapitalizedFieldName(element)+"())){\n"+
            "                result.set"+getCapitalizedFieldName(element)+"(new "+element.asSimpleType().replace("[]","")+"[original.get"+getCapitalizedFieldName(element)+"().length]);\n"+
                "                for(int i=0;i<original.get"+getCapitalizedFieldName(element)+"().length;i++){\n"+
                "                        result.get"+getCapitalizedFieldName(element)+"()[i]=original.get"+getCapitalizedFieldName(element)+"()[i]"+ (Objects.nonNull(element.getAnnotation(DeepCopy.class)) ? ".clone()" : "") +";\n"+
        "                }\n"+
        "        }\n";
    }
}
