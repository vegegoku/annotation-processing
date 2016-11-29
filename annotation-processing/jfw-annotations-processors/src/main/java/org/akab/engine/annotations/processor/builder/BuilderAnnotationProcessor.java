package org.akab.engine.annotations.processor.builder;

import com.google.auto.service.AutoService;
import org.akab.engine.annotations.builder.WithBuilder;
import org.akab.engine.annotations.processor.utils.BaseProcessor;
import org.akab.engine.annotations.processor.utils.ProcessorElement;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = "org.akab.engine.annotations.builder.WithBuilder")
public class BuilderAnnotationProcessor extends BaseProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(WithBuilder.class).stream()
                .filter(e -> validateElementKind(e, ElementKind.CLASS))
                .forEach(e -> generateBuilder(newProcessorElement(e)));
        return true;
    }

    private void generateBuilder(ProcessorElement processorElement) {
        try (Writer sourceWriter = obtainSourceWriter(processorElement.elementPackage(), processorElement.typeElementSimpleName()+ BuilderWriter.BUILDER)) {
            sourceWriter.write(new BuilderWriter(processorElement).write());
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }
}
