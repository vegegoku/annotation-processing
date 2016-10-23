package com.progressoft.annotations.processing;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class ProcessorElement {

    private final Element element;

    private ImportsWriter importsWriter=new ImportsWriter();

    public ProcessorElement(Element element) {
        this.element = element;
    }

    public String elementPackage() {
        return asTypeElement().getQualifiedName().toString().replace("." + simpleName(), "");
    }

    public TypeElement asTypeElement() {
        return (TypeElement) element;
    }

    public String simpleName() {
        return asTypeElement().getSimpleName().toString();
    }

    public <A extends Annotation> A getAnnotation(Class<A> annotation){
        return element.getAnnotation(annotation);
    }

    public Stream<? extends Element> fieldsStream(){
        return element.getEnclosedElements().stream().filter(element -> element.getKind()== ElementKind.FIELD);
    }

    public <A extends Annotation> Stream<? extends Element> fieldsAnnotatedWithStream(Class<A> annotationClass){
        return element.getEnclosedElements().stream().filter(element -> element.getKind()== ElementKind.FIELD).filter(element -> fieldAnnotatedWith(element, annotationClass));
    }

    private <A extends Annotation> boolean fieldAnnotatedWith(Element element, Class<A> annotationClass) {
        return Objects.nonNull(element.getAnnotation(annotationClass));
    }

    public ImportsWriter asImports(){
        StringTokenizer st = new StringTokenizer(element.asType().toString(), "<>,");
        if(st.hasMoreTokens())
            return asImports(st);
        return importsWriter.addImport(asImportToken(element.asType().toString()));
    }

    private ImportsWriter asImports(StringTokenizer st) {
        String token = st.nextToken();
        if (st.hasMoreTokens()){
            importsWriter.addImport(asImportToken(token));
            return asImports(st);
        }
        return importsWriter.addImport(asImportToken(token));
    }

    private String asImportToken(String token) {
        if(notPrimitiveOrLangImport(token))
            return "import " + token + ";\n";
        else
            return "";
    }

    private boolean notPrimitiveOrLangImport(String token) {
        return token.contains(".") && !token.startsWith("java.lang.");
    }

    public String asSimpleType() {
        StringTokenizer st = new StringTokenizer(element.asType().toString(), "<>,");
        if (st.hasMoreTokens())
            return removePackageFromName(st, element.asType().toString());
        return element.asType().toString();
    }

    private String removePackageFromName(StringTokenizer st, String input) {
        String token = st.nextToken();
        if (st.hasMoreTokens())
            return removePackageFromName(st, replacePart(input, token));
        return replacePart(input, token);
    }

    private String replacePart(String input, String token) {
        return input.replace(token.substring(0, token.lastIndexOf(".") + 1), "");
    }
}
