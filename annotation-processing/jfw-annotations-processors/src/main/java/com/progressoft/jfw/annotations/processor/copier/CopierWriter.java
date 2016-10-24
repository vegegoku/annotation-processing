package com.progressoft.jfw.annotations.processor.copier;

import com.progressoft.jfw.annotations.copier.IgnoreCopy;
import com.progressoft.jfw.annotations.processor.copier.generators.FieldCopyStatementFactory;
import com.progressoft.jfw.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import com.progressoft.jfw.annotations.processor.utils.JavaSourceWriter;
import com.progressoft.jfw.annotations.processor.utils.ProcessorElement;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.util.Objects;
import java.util.stream.Stream;

public class CopierWriter extends JavaSourceWriter {

    public CopierWriter(ProcessorElement processorElement) {
        super(processorElement);
    }

    @Override
    public String write() {
        return writePackage() +
                allFieldsImports(copierFieldsStream())+
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

    private Stream<? extends Element> copierFieldsStream() {
        return processorElement.fieldsStream().filter(e -> notStatic(e));
    }

    private boolean notStatic(Element e) {
        return Boolean.FALSE.equals(new ProcessorElement(e).hasModifier(Modifier.STATIC));
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
        copierFieldsStream().filter(element -> notIgnored(element))
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
