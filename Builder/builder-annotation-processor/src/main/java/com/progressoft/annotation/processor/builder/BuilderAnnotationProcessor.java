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
                buildMethodSignature(processorElement) +
                buildMethodBody(processorElement) +
                buildMethodClosing() +
                builderFieldsSetters(processorElement) +
                classClosing();
    }

    private String builderFieldsSetters(ProcessorElement processorElement) {
        StringBuilder sb = new StringBuilder();
        processorElement.fieldsStream().forEach(element -> sb.append("\n    ")
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
        processorElement.fieldsStream().forEach(element -> sb.append("    private ").append(new ProcessorElement(element).asSimpleType()).append(" ").append(element.getSimpleName()).append(";\n"));
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

    private String removeLastComma(String commaSeperatedParameters) {
        if(commaSeperatedParameters.endsWith(","))
            return commaSeperatedParameters.substring(0, commaSeperatedParameters.lastIndexOf(","));
        return commaSeperatedParameters;
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
}
