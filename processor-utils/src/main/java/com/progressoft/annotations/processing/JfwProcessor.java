package com.progressoft.annotations.processing;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;

public abstract class JfwProcessor extends AbstractProcessor {

    protected Types typeUtils;
    protected Elements elementUtils;
    protected Filer filer;
    protected Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.typeUtils=processingEnv.getTypeUtils();
        this.elementUtils=processingEnv.getElementUtils();
        this.filer=processingEnv.getFiler();
        this.messager=processingEnv.getMessager();
    }

    protected Writer obtainSourceWriter(String targetPackage,String className) throws IOException {
        return createSourceFile(targetPackage, className).openWriter();
    }

    protected JavaFileObject createSourceFile(String targetPackage, String className) throws IOException {
        return filer.createSourceFile(targetPackage + "." + className);
    }

    protected void validateElementKind(Element element, ElementKind kind) {
        if (element.getKind() != kind)
            throw new ProcessingException(element, "Only "+kind+" can be annotated with @%s");
    }

    protected String imports(ProcessorElement processorElement) {
        ImportsWriter importsWriter = new ImportsWriter();
        processorElement.fieldsStream().filter(element -> element.asType().toString().contains(".")).forEach(element -> importsWriter.addAll(new ProcessorElement(element).asImports().allImports()));
        return importsWriter.asImportsString();
    }

}

