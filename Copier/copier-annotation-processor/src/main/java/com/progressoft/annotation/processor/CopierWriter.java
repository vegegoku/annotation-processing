package com.progressoft.annotation.processor;

import com.progressoft.annotation.processor.copier.IgnoreCopy;
import com.progressoft.annotation.processor.generators.FieldCopyStatementFactory;
import com.progressoft.annotation.processor.generators.FieldsCopyStatementGenerator;
import com.progressoft.annotations.processing.JavaSourceWriter;
import com.progressoft.annotations.processing.ProcessorElement;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Objects;

public class CopierWriter extends JavaSourceWriter {


    public CopierWriter(ProcessorElement processorElement) {
        super(processorElement);
    }

    @Override
    public String write() {
        return writePackage() +
                allFieldsImports()+
                writeClassName() +
                writeCopyMethodSignature() +
                writeThrowsDeclaration() +
                writeInstanceCreationStatement() +
                writeCopyFieldsStatements() +
                writeReturnStatement() +
                closeMethod() +
                closeClass();
    }

    private String writePackage() {
        return "package " + processorElement.elementPackage() + ";\n\n";
    }

    private String writeClassName() {
        return "class " + processorElement.simpleName() + FieldsCopyStatementGenerator.COPIER_POSTFIX + " {\n\n";
    }

    private String writeCopyMethodSignature() {
        return "    " + processorElement.simpleName() + " copy(" + processorElement.simpleName() + " " + FieldsCopyStatementGenerator.ORIGINAL + ")";
    }

    private String writeThrowsDeclaration() {
        return "throws CloneNotSupportedException {\n";
    }

    private String writeInstanceCreationStatement() {
        return "        " + processorElement.simpleName() + " " + FieldsCopyStatementGenerator.RESULT + "=new " + processorElement.simpleName() + "();\n\n";
    }

    private String writeCopyFieldsStatements() {
        StringBuilder sb = new StringBuilder();
        generatedCopStatements(processorElement.asTypeElement(), sb);
        return sb.append("\n").toString();
    }

    private void generatedCopStatements(TypeElement annotatedClassElement, StringBuilder sb) {
        new ProcessorElement(annotatedClassElement).fieldsStream().filter(element -> notIgnored(element))
                .forEach(element -> sb.append(FieldCopyStatementFactory.getGenerator(annotatedClassElement, element)
                        .generate(element)));
    }

    private boolean notIgnored(Element element) {
        return Objects.isNull(element.getAnnotation(IgnoreCopy.class));
    }

    private String writeReturnStatement() {
        return "        return " + FieldsCopyStatementGenerator.RESULT + ";\n";
    }

    private String closeMethod() {
        return "    }\n";
    }

    private String closeClass() {
        return "}";
    }
}
