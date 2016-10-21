package com.progressoft.annotation.processor.builder;

import com.google.auto.service.AutoService;
import com.progressoft.annotations.processing.ImportsWriter;
import com.progressoft.annotations.processing.JfwProcessor;
import com.progressoft.annotations.processing.ProcessorElement;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.sql.BatchUpdateException;
import java.util.Objects;
import java.util.Set;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = "com.progressoft.annotation.processor.builder.WithBuilder")
public class BuilderAnnotationProcessor extends JfwProcessor {


    public static final String BUILDER = "Builder";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(WithBuilder.class)) {
            validateElementKind(element, ElementKind.CLASS);
            generateBuilder(new ProcessorElement(element));
        }
        return true;
    }

    private void generateBuilder(ProcessorElement processorElement) {
        try (Writer sourceWriter = obtainSourceWriter(processorElement.elementPackage(), processorElement.simpleName()+ BUILDER)) {
            String result=writeBuilderClass(processorElement);

            sourceWriter.write(result);
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }

    private String writeBuilderClass(ProcessorElement processorElement) {
        return builderPackage(processorElement) +
                imports(processorElement) +
                builderClassDefenition(processorElement) +
                builderFields(processorElement) +
                builderConstructor(processorElement)+
                buildMethodSignature(processorElement) +
                buildMethodBody(processorElement) +
                buildMethodClosing() +
                builderFieldsSetters(processorElement) +
                classClosing();
    }

    private String builderConstructor(ProcessorElement processorElement) {
        StringBuilder sb=new StringBuilder();
        sb.append("    ").append(processorElement.simpleName()).append(BUILDER).append("(")
                .append(constructorFields(processorElement))
        .append("){\n").append(constructorFieldsInitialization(processorElement)).append("    }\n\n");

        return sb.toString();
    }

    private String constructorFields(ProcessorElement processorElement) {
        StringBuilder sb=new StringBuilder();
        processorElement.fieldsAnnotatedWithStream(BuilderRequired.class).forEach(element -> sb.append(new ProcessorElement(element).asSimpleType()).append(" ").append(element.getSimpleName()).append(", "));
        return sb.toString().endsWith(", ")?sb.toString().substring(0,sb.toString().length()-2):sb.toString();
    }

    private String constructorFieldsInitialization(ProcessorElement processorElement) {
        StringBuilder sb=new StringBuilder();
        processorElement.fieldsAnnotatedWithStream(BuilderRequired.class).forEach(element -> sb.append("        this.").append(element.getSimpleName()).append(" = ").append(element.getSimpleName()).append(";\n"));
        return sb.toString();
    }

    private String builderFieldsSetters(ProcessorElement processorElement) {
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

    private String builderFields(ProcessorElement processorElement) {
        StringBuilder sb = new StringBuilder();
        processorElement.fieldsStream().forEach(element -> sb.append("    private ").append(isRequired(element)?"final ":"").append(new ProcessorElement(element).asSimpleType()).append(" ").append(element.getSimpleName()).append(";\n"));
        return sb.append("\n").toString();
    }

    private String classClosing() {
        return "}";
    }

    private String buildMethodClosing() {
        return "    }\n";
    }

    private String buildMethodBody(ProcessorElement processorElement) {
        return "        return new " + processorElement.simpleName() + "("+fieldsParameters(processorElement)+");\n";
    }

    private String fieldsParameters(ProcessorElement processorElement) {
        StringBuilder sb = new StringBuilder();
        processorElement.fieldsStream().forEach(element -> sb.append(element.getSimpleName()+","));
        return removeLastComma(sb.toString());
    }

    private String removeLastComma(String commaSeparatedParameters) {
        if(commaSeparatedParameters.endsWith(","))
            return commaSeparatedParameters.substring(0, commaSeparatedParameters.lastIndexOf(","));
        return commaSeparatedParameters;
    }

    private String buildMethodSignature(ProcessorElement processorElement) {
        return "    " + processorElement.simpleName() + " build(){\n";
    }

    private String builderClassDefenition(ProcessorElement processorElement) {
        return "class " + processorElement.simpleName() + "Builder {\n\n";
    }

    private String builderPackage(ProcessorElement processorElement) {
        return "package " + processorElement.elementPackage() + ";\n\n";
    }

    private boolean isRequired(Element element){
        return Objects.nonNull(element.getAnnotation(BuilderRequired.class));
    }

    private boolean notRequired(Element element){
        return !isRequired(element);
    }
}
