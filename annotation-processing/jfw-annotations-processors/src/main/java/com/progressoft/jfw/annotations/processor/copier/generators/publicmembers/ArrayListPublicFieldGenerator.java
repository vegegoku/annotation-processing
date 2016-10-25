package com.progressoft.jfw.annotations.processor.copier.generators.publicmembers;


import com.progressoft.jfw.annotations.copier.DeepCopy;
import com.progressoft.jfw.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import com.progressoft.jfw.annotations.processor.utils.ProcessorElement;

import javax.lang.model.element.Element;
import java.util.Objects;

public class ArrayListPublicFieldGenerator implements FieldsCopyStatementGenerator {
    @Override
    public String generate(ProcessorElement element) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n        if(java.util.Objects.nonNull(original." + element.simpleName().toString() + ")){\n");
        sb.append("                result." + element.simpleName().toString() + "=new java.util.ArrayList<>();\n");
        sb.append("                for (" + getGenericType(element) + " item :original." + element.simpleName().toString() + ") {\n");
        sb.append("                                result." + element.simpleName().toString() + ".add(item" + (Objects.nonNull(element.getAnnotation(DeepCopy.class)) ? ".clone()" : "") + ");\n");
        sb.append("                }\n");
        sb.append("        }\n");
        return sb.toString();
    }
}
