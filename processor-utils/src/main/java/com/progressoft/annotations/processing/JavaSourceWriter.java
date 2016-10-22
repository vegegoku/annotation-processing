package com.progressoft.annotations.processing;

public abstract class JavaSourceWriter {

    protected final ProcessorElement processorElement;

    public JavaSourceWriter(ProcessorElement processorElement) {
        this.processorElement = processorElement;
    }

    protected String allFieldsImports() {
        ImportsWriter importsWriter = new ImportsWriter();
        processorElement.fieldsStream().filter(element -> element.asType().toString().contains(".")).forEach(element -> importsWriter.addAll(new ProcessorElement(element).asImports().allImports()));
        return importsWriter.asImportsString();
    }

    public abstract String write();
}
