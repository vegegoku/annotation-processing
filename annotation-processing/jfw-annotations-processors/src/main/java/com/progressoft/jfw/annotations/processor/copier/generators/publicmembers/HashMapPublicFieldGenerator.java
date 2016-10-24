package com.progressoft.jfw.annotations.processor.copier.generators.publicmembers;

import com.progressoft.jfw.annotations.copier.DeepCopy;
import com.progressoft.jfw.annotations.processor.copier.generators.FieldsCopyStatementGenerator;

import javax.lang.model.element.Element;
import java.util.Objects;

public class HashMapPublicFieldGenerator implements FieldsCopyStatementGenerator {
    @Override
    public String generate(Element element) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n        if(java.util.Objects.nonNull(original." + element.getSimpleName().toString() + ")){\n");
        sb.append("                result." + element.getSimpleName().toString() + "=new java.util.HashMap<>();\n");
        sb.append("                for (java.util.Map.Entry<"+getGenericType(element)+"> entry:original." + element.getSimpleName().toString() + ".entrySet()) {\n");
        sb.append("                                result." + element.getSimpleName().toString() + ".put(entry.getKey(), entry.getValue()" + (Objects.nonNull(element.getAnnotation(DeepCopy.class)) ? ".clone()" : "") + ");\n");
        sb.append("                }\n");
        sb.append("        }\n");
        return sb.toString();
    }
}
