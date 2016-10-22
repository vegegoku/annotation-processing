package com.progressoft.annotation.processor;

import com.google.auto.service.AutoService;
import com.progressoft.annotations.processing.JfwProcessor;
import com.progressoft.annotations.processing.ProcessorElement;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
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
@SupportedAnnotationTypes("com.progressoft.annotation.processor.UiForm")
public class UiFormProcessor extends JfwProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(UiForm.class)) {
            validateElementKind(element, ElementKind.CLASS);
            generateForm(new ProcessorElement(element));
        }
        return true;
    }

    private void generateForm(ProcessorElement processorElement) {
        try (Writer sourceWriter = obtainResourceWriter(processorElement.elementPackage(), processorElement.simpleName() + "Form.html")) {
            sourceWriter.write("<form action=\"doSomethingAction\">\n    <input type=\"submit\" value=\"Submit\">\n</form>");
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }
}
