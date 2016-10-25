package com.progressoft.jfw.annotations.processor.builder;


import com.progressoft.jfw.annotations.builder.BuilderRequired;
import com.progressoft.jfw.annotations.processor.utils.JavaSourceWriter;
import com.progressoft.jfw.annotations.processor.utils.ProcessorElement;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import java.util.Objects;
import java.util.stream.Stream;

public class BuilderWriter extends JavaSourceWriter {

    public static final String BUILDER = "Builder";

    public BuilderWriter(ProcessorElement processorElement) {
        super(processorElement);
    }

    @Override
    public String write() {
        return builderPackage() +
                allFieldsImports(builderFieldsStream()) +
                builderClassDefinition() +
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

    private String builderClassDefinition() {
        return "public class " + processorElement.typeElementSimpleName() + "Builder {\n\n";
    }

    private String builderFields() {
        StringBuilder sb = new StringBuilder();
        builderFieldsStream().forEach(e -> sb.append("    private ").append(isRequired(e)?"final ":"").append(processorElement.make(e).asSimpleType()).append(" ").append(e.getSimpleName()).append(";\n"));
        return sb.append("\n").toString();
    }

    private Stream<? extends Element> builderFieldsStream() {
        return processorElement.fieldsStream().filter(e -> notStatic(e));
    }

    private boolean notStatic(Element e) {
        return Boolean.FALSE.equals(processorElement.make(e).hasModifier(Modifier.STATIC));
    }

    private boolean isRequired(Element element){
        return Objects.nonNull(element.getAnnotation(BuilderRequired.class));
    }

    private boolean notRequired(Element element){
        return !isRequired(element);
    }

    private String builderConstructor() {
        StringBuilder sb=new StringBuilder();
        sb.append("    public ").append(processorElement.typeElementSimpleName()).append(BUILDER).append("(")
                .append(constructorFields())
                .append("){\n").append(constructorFieldsInitialization()).append("    }\n\n");

        return sb.toString();
    }

    private String constructorFields() {
        StringBuilder sb=new StringBuilder();
        processorElement.fieldsAnnotatedWithStream(BuilderRequired.class).forEach(element -> sb.append(processorElement.make(element).asSimpleType()).append(" ").append(element.getSimpleName()).append(", "));
        return sb.toString().endsWith(", ")?sb.toString().substring(0,sb.toString().length()-2):sb.toString();
    }

    private String constructorFieldsInitialization() {
        StringBuilder sb=new StringBuilder();
        processorElement.fieldsAnnotatedWithStream(BuilderRequired.class).forEach(element -> sb.append("        this.").append(element.getSimpleName()).append(" = ").append(element.getSimpleName()).append(";\n"));
        return sb.toString();
    }

    private String buildMethodSignature() {
        return "    public " + processorElement.asSimpleType() + " build(){\n";
    }

    private String buildMethodBody() {
        return "        return new " + processorElement.asSimpleType() + "("+fieldsParameters()+");\n";
    }

    private String fieldsParameters() {
        StringBuilder sb = new StringBuilder();
        builderFieldsStream().forEach(element -> sb.append(element.getSimpleName()+","));
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
        builderFieldsStream().filter(element -> notRequired(element)).forEach(element -> sb.append("\n    public ")
                .append(processorElement.asSimpleType()).append(BUILDER).append(" ")
                .append(element.getSimpleName()).append("(")
                .append(processorElement.make(element).asSimpleType())
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
