package org.akab.engine.annotations.processor.copier.generators.publicmembers;


import org.akab.engine.annotations.copier.DeepCopy;
import org.akab.engine.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import org.akab.engine.annotations.processor.utils.ProcessorElement;

import java.util.Objects;

public class SimplePublicFieldCopyGenerator implements FieldsCopyStatementGenerator {

    @Override
    public String generate(ProcessorElement element) {
        StringBuilder sb = new StringBuilder();

        if(Objects.nonNull(element.getAnnotation(DeepCopy.class))){
            sb.append("\n       if(java.util.Objects.nonNull(original."+element.simpleName().toString()+")) {\n        ");
        }

        sb.append("       " + RESULT + ".");
        sb.append(element.simpleName().toString());
        sb.append("=" + ORIGINAL + ".");
        sb.append(element.simpleName().toString());

        if(Objects.nonNull(element.getAnnotation(DeepCopy.class))){
            sb.append(".clone();\n       }\n\n");
        }else {
            sb.append(";\n");
        }

        return sb.toString();
    }
}
