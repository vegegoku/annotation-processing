package com.progressoft.jfw.annotations.processor.copier;

import com.google.auto.service.AutoService;
import com.progressoft.jfw.annotations.copier.WithCopier;
import com.progressoft.jfw.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import com.progressoft.jfw.annotations.processor.utils.JfwProcessor;
import com.progressoft.jfw.annotations.processor.utils.ProcessorElement;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@AutoService(value = Processor.class)
@SupportedAnnotationTypes(value = "com.progressoft.jfw.annotations.copier.WithCopier")
@SupportedSourceVersion(value = SourceVersion.RELEASE_8)
public class CopierAnnotationProcessor extends JfwProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(WithCopier.class).stream()
                .filter(e -> validateElementKind(e, ElementKind.CLASS))
                .forEach(e -> generateCopier(newProcessorElement(e)));
        return true;
    }

    private void generateCopier(ProcessorElement processorElement) {
        try (Writer sourceWriter = obtainSourceWriter(processorElement.elementPackage(), processorElement.typeElementSimpleName() + FieldsCopyStatementGenerator.COPIER_POSTFIX)) {
            sourceWriter.write(new CopierWriter(processorElement).write());
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }
}
