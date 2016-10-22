package com.progressoft.annotation.processor.builder;

import com.progressoft.annotations.processing.JavaSourceWriter;
import com.progressoft.annotations.processing.ProcessorElement;

import javax.lang.model.element.Element;
import java.util.Objects;

public class BuilderWriter extends JavaSourceWriter{

    public static final String BUILDER = "Builder";

    public BuilderWriter(ProcessorElement processorElement) {
        super(processorElement);
    }

    @Override
    public String write() {
        return builderPackage() +
                allFieldsImports() +
                builderClassDefenition() +
                builderFields() +
                builderConstructor()+
                buildMethodSignature() +
                buildMethodBody() +
                buildMethodClosing() +
                builderFieldsSetters() +
                classClosing();
    }

    private String builderPackage() {
        return "package " + processorElement.elementPackage() + ";\n\n";
    }

    private String builderClassDefenition() {
        return "class " + processorElement.simpleName() + "Builder {\n\n";
    }

    private String builderFields() {
        StringBuilder sb = new StringBuilder();
        processorElement.fieldsStream().forEach(element -> sb.append("    private ").append(isRequired(element)?"final ":"").append(new ProcessorElement(element).asSimpleType()).append(" ").append(element.getSimpleName()).append(";\n"));
        return sb.append("\n").toString();
    }

    private boolean isRequired(Element element){
        return Objects.nonNull(element.getAnnotation(BuilderRequired.class));
    }

    private boolean notRequired(Element element){
        return !isRequired(element);
    }

    private String builderConstructor() {
        StringBuilder sb=new StringBuilder();
        sb.append("    ").append(processorElement.simpleName()).append(BUILDER).append("(")
                .append(constructorFields())
                .append("){\n").append(constructorFieldsInitialization()).append("    }\n\n");

        return sb.toString();
    }

    private String constructorFields() {
        StringBuilder sb=new StringBuilder();
        processorElement.fieldsAnnotatedWithStream(BuilderRequired.class).forEach(element -> sb.append(new ProcessorElement(element).asSimpleType()).append(" ").append(element.getSimpleName()).append(", "));
        return sb.toString().endsWith(", ")?sb.toString().substring(0,sb.toString().length()-2):sb.toString();
    }

    private String constructorFieldsInitialization() {
        StringBuilder sb=new StringBuilder();
        processorElement.fieldsAnnotatedWithStream(BuilderRequired.class).forEach(element -> sb.append("        this.").append(element.getSimpleName()).append(" = ").append(element.getSimpleName()).append(";\n"));
        return sb.toString();
    }

    private String buildMethodSignature() {
        return "    " + processorElement.simpleName() + " build(){\n";
    }

    private String buildMethodBody() {
        return "        return new " + processorElement.simpleName() + "("+fieldsParameters()+");\n";
    }

    private String fieldsParameters() {
        StringBuilder sb = new StringBuilder();
        processorElement.fieldsStream().forEach(element -> sb.append(element.getSimpleName()+","));
        return removeLastComma(sb.toString());
    }

    private String removeLastComma(String commaSeparatedParameters) {
        if(commaSeparatedParameters.endsWith(","))
            return commaSeparatedParameters.substring(0, commaSeparatedParameters.lastIndexOf(","));
        return commaSeparatedParameters;
    }

    private String buildMethodClosing() {
        return "    }\n";
    }

    private String builderFieldsSetters() {
        StringBuilder sb = new StringBuilder();
        processorElement.fieldsStream().filter(element -> notRequired(element)).forEach(element -> sb.append("\n    ")
                .append(processorElement.asSimpleType()).append(BUILDER).append(" ")
                .append(element.getSimpleName()).append("(")
                .append(new ProcessorElement(element).asSimpleType())
                .append(" ").append(element.getSimpleName()).append("){\n")
                .append("        ").append("this.").append(element.getSimpleName())
                .append(" = ").append(element.getSimpleName()).append(";\n")
                .append("        ").append("return this;\n").append("    }\n"));
        return sb.toString();
    }

    private String classClosing() {
        return "}";
    }
}
