package com.progressoft.jfw.annotations.processor.copier.generators.accessor;


import com.progressoft.jfw.annotations.copier.DeepCopy;
import com.progressoft.jfw.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import com.progressoft.jfw.annotations.processor.utils.ProcessorElement;
import org.apache.commons.lang.WordUtils;

import javax.lang.model.element.Element;
import java.util.Objects;

public class AccessorSingleFieldCopyGenerator implements FieldsCopyStatementGenerator {

    @Override
    public String generate(ProcessorElement element) {
        StringBuilder sb = new StringBuilder();

        if(Objects.nonNull(element.getAnnotation(DeepCopy.class))){
            sb.append("\n        if(java.util.Objects.nonNull(original.get"+ WordUtils.capitalize(element.simpleName().toString()+"())) {\n        "));
        }

        sb.append("        " + RESULT + ".set");
        sb.append(WordUtils.capitalize(element.simpleName().toString()));
        sb.append("(" + ORIGINAL + "." + ("boolean".equals(element.asTypeString()) ? "is" : "get"));
        sb.append(WordUtils.capitalize(element.simpleName().toString()));

        if(Objects.nonNull(element.getAnnotation(DeepCopy.class))){
            sb.append("().clone());\n        }\n\n");
        }else {
            sb.append("());\n");
        }


        return sb.toString();
    }
}
