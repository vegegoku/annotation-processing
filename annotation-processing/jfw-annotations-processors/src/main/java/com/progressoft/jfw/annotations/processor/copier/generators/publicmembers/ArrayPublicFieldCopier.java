package com.progressoft.jfw.annotations.processor.copier.generators.publicmembers;

import com.progressoft.jfw.annotations.copier.DeepCopy;
import com.progressoft.jfw.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import com.progressoft.jfw.annotations.processor.utils.ProcessorElement;

import javax.lang.model.element.Element;
import java.util.Objects;

public class ArrayPublicFieldCopier implements FieldsCopyStatementGenerator {
    @Override
    public String generate(Element element) {
        return "\n        if(java.util.Objects.nonNull(original."+element.getSimpleName()+")){\n"+
                "                result."+element.getSimpleName()+"=new "+new ProcessorElement(element).asSimpleType().replace("[]","")+"[original."+element.getSimpleName()+".length];\n"+
                "                for(int i=0;i<original."+element.getSimpleName()+".length;i++){\n"+
                "                        result."+element.getSimpleName()+"[i]=original."+element.getSimpleName()+"[i]"+ (Objects.nonNull(element.getAnnotation(DeepCopy.class)) ? ".clone()" : "") +";\n"+
                "                }\n"+
                "        }\n";
    }
}
