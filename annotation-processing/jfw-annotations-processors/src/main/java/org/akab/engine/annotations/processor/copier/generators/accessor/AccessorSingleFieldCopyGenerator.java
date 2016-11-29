package org.akab.engine.annotations.processor.copier.generators.accessor;


import org.akab.engine.annotations.copier.DeepCopy;
import org.akab.engine.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import org.akab.engine.annotations.processor.utils.ProcessorElement;
import org.apache.commons.lang.WordUtils;

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
