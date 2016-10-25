package com.progressoft.jfw.annotations.processor.copier.generators.accessor;


import com.progressoft.jfw.annotations.copier.DeepCopy;
import com.progressoft.jfw.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import com.progressoft.jfw.annotations.processor.utils.ProcessorElement;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import java.util.Objects;

public class ArrayListAccessorCopier implements FieldsCopyStatementGenerator {

    @Override
    public String generate(ProcessorElement element) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n        if(java.util.Objects.nonNull(original.get" + getCapitalizedFieldName(element) + "())){\n");
        sb.append("                result.set" + getCapitalizedFieldName(element) + "(new java.util.ArrayList<>());\n");
        sb.append("                for (" + getGenericType(element) + " item :original.get" + getCapitalizedFieldName(element) + "()) {\n");
        sb.append("                                result.get" + getCapitalizedFieldName(element) + "().add(item" + (Objects.nonNull(element.getAnnotation(DeepCopy.class)) ? ".clone()" : "") + ");\n");
        sb.append("                }\n");
        sb.append("        }\n");
        return sb.toString();
    }

}
