package org.akab.engine.annotations.processor.builder;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.akab.engine.annotations.processor.test.ProcessorAssert.*;

public class BuilderProcessorTest {

    public static final String BASE_PACKAGE = "org/akab/engine/annotations/processor/builder/";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void noneAnnotatedClasses_shouldCompileWithoutErrors() throws Exception {
        assertProcessing(BASE_PACKAGE + "NotAnnotatedClass.java").withProcessor(new BuilderAnnotationProcessor()).compilesWithoutErrors();
    }

    @Test
    public void emptyAnnotatedClass_shouldGenerateSimpleEmptyBuilder() throws Exception {
        assertProcessing(BASE_PACKAGE + "EmptyAnnotatedClass.java")
                .withProcessor(new BuilderAnnotationProcessor())
                .generates(getExpectedResultFileContent("EmptyAnnotatedClassBuilder.java"));
    }

    @Test
    public void annotatedClassHavingSingleProperty_shouldGenerateBuilderForTatProperty() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithSingleProperty.java")
                .withProcessor(new BuilderAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithSinglePropertyBuilder.java"));
    }

    @Test
    public void annotatedClassHavingManyProperties_shouldGenerateBuilderForAllProperties() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithManyFields.java")
                .withProcessor(new BuilderAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithManyFieldsBuilder.java"));
    }

    @Test
    public void annotatedClassHavingRequiredProperties_shouldGenerateBuilderWithConstructorOfAllRequiredProperties() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithRequiredFields.java")
                .withProcessor(new BuilderAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithRequiredFieldsBuilder.java"));
    }

    @Test
    public void annotatedClassHavingStaticProperties_shouldGenerateBuilderThatIgnoresAllTheStaticFields() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithStaticFields.java")
                .withProcessor(new BuilderAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithStaticFieldsBuilder.java"));
    }

    @Test
    public void annotatedClassHavingInnerClass_shouldGenerateBuilderForBothClasses() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithInnerClass.java")
                .withProcessor(new BuilderAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithInnerClassBuilder.java"), getExpectedResultFileContent("InnerClassBuilder.java"));
    }

    private String getExpectedResultFileContent(String resourceName) throws IOException {
        try(InputStream resourceInputStream=this.getClass().getResourceAsStream("results/"+resourceName)){
            return IOUtils.toString(resourceInputStream,"UTF-8");
        }
    }
}
