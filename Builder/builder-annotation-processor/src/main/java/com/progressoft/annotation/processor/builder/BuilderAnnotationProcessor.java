package com.progressoft.annotation.processor.builder;

import com.google.auto.service.AutoService;
import com.progressoft.annotations.processing.JfwProcessor;
import com.progressoft.annotations.processing.ProcessorElement;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
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

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(WithBuilder.class).stream()
                .filter(e -> validateElementKind(e, ElementKind.CLASS))
                .forEach(e -> generateBuilder(new ProcessorElement(e)));
        return true;
    }

    private void generateBuilder(ProcessorElement processorElement) {
        try (Writer sourceWriter = obtainSourceWriter(processorElement.elementPackage(), processorElement.simpleName()+ BuilderWriter.BUILDER)) {

            sourceWriter.write(new BuilderWriter(processorElement).write());
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }
}
