package com.progressoft.jfw.annotations.processor.utils;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessorElement {

    private final Element element;
    private Elements elementUtils;
    private Types typeUtils;

    private ImportsWriter importsWriter=new ImportsWriter();

    public ProcessorElement(Element element, Elements elementUtils, Types typeUtils) {
        this.element = element;
        this.elementUtils=elementUtils;
        this.typeUtils=typeUtils;
    }

    public ProcessorElement make(Element element){
        return new ProcessorElement(element, elementUtils, typeUtils);
    }

    public String elementPackage() {
        return elementUtils.getPackageOf(element).getQualifiedName().toString();
    }

    public TypeElement asTypeElement() {
        return (TypeElement) element;
    }

    public String asTypeString(){
        return element.asType().toString();
    }

    public String typeElementSimpleName() {
        return asTypeElement().getSimpleName().toString();
    }

    public String simpleName() {
        return element.getSimpleName().toString();
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
            return "import " + token.replace(" ","") + ";\n";
        else
            return "";
    }

    private boolean notPrimitiveOrLangImport(String token) {
        return token.contains(".") && !token.startsWith("java.lang.");
    }

    public String asSimpleType() {
       if(isInnerClass())
           return element.getEnclosingElement().getSimpleName()+"."+getSimpleType();
       return getSimpleType();
    }

    private boolean isInnerClass() {
        return element.getKind()==ElementKind.CLASS && element.getEnclosingElement().getKind()== ElementKind.CLASS;
    }

    private String getSimpleType(){
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

    public Set<String> modifiers(){
        return element.getModifiers().stream().map(m -> m.toString()).collect(Collectors.toSet());
    }

    public boolean hasModifier(Modifier modifier){
        return element.getModifiers().contains(modifier);
    }
}
