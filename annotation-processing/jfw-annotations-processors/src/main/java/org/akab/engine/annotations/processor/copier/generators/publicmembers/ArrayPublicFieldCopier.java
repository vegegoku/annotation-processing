package org.akab.engine.annotations.processor.copier.generators.publicmembers;

import org.akab.engine.annotations.copier.DeepCopy;
import org.akab.engine.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import org.akab.engine.annotations.processor.utils.ProcessorElement;

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
