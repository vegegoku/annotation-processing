package org.akab.engine.annotations.processor.copier;

import com.google.auto.service.AutoService;
import org.akab.engine.annotations.copier.WithCopier;
import org.akab.engine.annotations.processor.copier.generators.FieldsCopyStatementGenerator;
import org.akab.engine.annotations.processor.utils.BaseProcessor;
import org.akab.engine.annotations.processor.utils.ProcessorElement;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@AutoService(value = Processor.class)
@SupportedAnnotationTypes(value = "org.akab.engine.annotations.copier.WithCopier")
@SupportedSourceVersion(value = SourceVersion.RELEASE_8)
public class CopierAnnotationProcessor extends BaseProcessor {

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
