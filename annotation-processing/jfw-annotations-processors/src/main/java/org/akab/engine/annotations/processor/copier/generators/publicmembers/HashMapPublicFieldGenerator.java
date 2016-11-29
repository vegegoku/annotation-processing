package org.akab.engine.annotations.processor.copier.generators.publicmembers;

import org.akab.engine.annotations.copier.DeepCopy;
import org.akab.engine.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import org.akab.engine.annotations.processor.utils.ProcessorElement;

import java.util.Objects;

public class HashMapPublicFieldGenerator implements FieldsCopyStatementGenerator {
    @Override
    public String generate(ProcessorElement element) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n        if(java.util.Objects.nonNull(original." + element.simpleName().toString() + ")){\n");
        sb.append("                result." + element.simpleName().toString() + "=new java.util.HashMap<>();\n");
        sb.append("                for (java.util.Map.Entry<"+getGenericType(element)+"> entry:original." + element.simpleName().toString() + ".entrySet()) {\n");
        sb.append("                                result." + element.simpleName().toString() + ".put(entry.getKey(), entry.getValue()" + (Objects.nonNull(element.getAnnotation(DeepCopy.class)) ? ".clone()" : "") + ");\n");
        sb.append("                }\n");
        sb.append("        }\n");
        return sb.toString();
    }
}
