package org.akab.engine.annotations.processor.utils;

import javax.lang.model.element.Element;
import java.util.stream.Stream;

public abstract class JavaSourceWriter {

    protected final ProcessorElement processorElement;

    public JavaSourceWriter(ProcessorElement processorElement) {
        this.processorElement = processorElement;
    }

    protected String allFieldsImports() {
        return allFieldsImports(processorElement.fieldsStream());
    }

    protected String allFieldsImports(Stream<? extends Element> elements) {
        ImportsWriter importsWriter = new ImportsWriter();
        elements.filter(element -> element.asType().toString().contains(".")).forEach(element -> importsWriter.addAll(processorElement.make(element).asImports().allImports()));
        return importsWriter.asImportsString();
    }

    public abstract String write();
}
