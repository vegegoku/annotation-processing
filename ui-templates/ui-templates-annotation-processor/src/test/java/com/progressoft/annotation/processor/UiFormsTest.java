package com.progressoft.annotation.processor;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static com.progressoft.annotation.processor.test.ProcessorAssert.*;

public class UiFormsTest {

    private static final String BASE_PACKAGE_PATH = "com/progressoft/annotation/processor/";
    public static final String BASE_PACKAGE = "com.progressoft.annotation.processor";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void noneAnnotatedClasses_shouldCompileWithoutErrors() throws Exception {
        assertProcessing(BASE_PACKAGE_PATH + "EmptyClass.java").withProcessor(new UiFormProcessor()).compilesWithoutErrors();
    }

    @Test
    public void emptyAnnotatedClass_shouldGenerateEmptyHtmlFormTemplate() throws Exception {
        assertProcessing(BASE_PACKAGE_PATH + "EmptyAnnotatedClass.java")
                .withProcessor(new UiFormProcessor())
                .generatesResource(BASE_PACKAGE, "EmptyAnnotatedClassForm.html", getExpectedResultFileContent("EmptyAnnotatedClassForm.html"));
    }

    private String getExpectedResultFileContent(String resourceName) throws IOException {
        try (InputStream resourceInputStream = this.getClass().getResourceAsStream("results/" + resourceName)) {
            return IOUtils.toString(resourceInputStream, "UTF-8");
        }
    }
}