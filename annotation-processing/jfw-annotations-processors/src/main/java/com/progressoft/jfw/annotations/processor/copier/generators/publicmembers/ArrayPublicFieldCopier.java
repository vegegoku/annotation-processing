package com.progressoft.jfw.annotations.processor.copier.generators.publicmembers;

import com.progressoft.jfw.annotations.copier.DeepCopy;
import com.progressoft.jfw.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import com.progressoft.jfw.annotations.processor.utils.ProcessorElement;

import javax.lang.model.element.Element;
import java.util.Objects;

public class ArrayPublicFieldCopier implements FieldsCopyStatementGenerator {
    @Override
    public String generate(ProcessorElement element) {
        return "\n        if(java.util.Objects.nonNull(original."+element.simpleName()+")){\n"+
                "                result."+element.simpleName()+"=new "+element.asSimpleType().replace("[]","")+"[original."+element.simpleName()+".length];\n"+
                "                for(int i=0;i<original."+element.simpleName()+".length;i++){\n"+
                "                        result."+element.simpleName()+"[i]=original."+element.simpleName()+"[i]"+ (Objects.nonNull(element.getAnnotation(DeepCopy.class)) ? ".clone()" : "") +";\n"+
                "                }\n"+
                "        }\n";
    }
}
