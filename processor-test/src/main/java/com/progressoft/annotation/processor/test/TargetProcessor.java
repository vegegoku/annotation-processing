package com.progressoft.annotation.processor.test;

import com.google.common.truth.Truth;
import com.google.testing.compile.CompileTester;
import com.google.testing.compile.JavaFileObjects;

import javax.annotation.processing.Processor;
import javax.tools.StandardLocation;

import java.nio.charset.Charset;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class TargetProcessor {
    private final String inputClassName;
    private final Processor processor;

    public TargetProcessor(String inputClassName, Processor processor) {
        this.inputClassName = inputClassName;
        this.processor = processor;
    }

    public void generates(String result){
        compilesWithoutErrors()
                .and()
                .generatesSources(JavaFileObjects.forSourceString("",result));
    }

    public CompileTester.SuccessfulCompilationClause compilesWithoutErrors(){
        return Truth.assert_().about(javaSource()).that(JavaFileObjects.forResource(inputClassName))
                .processedWith(processor).compilesWithoutError();
    }

    public void generatesResource(String basePackage, String fileName, String content){
        compilesWithoutErrors()
                .and()
                .generatesFileNamed(StandardLocation.SOURCE_OUTPUT, basePackage, fileName).withStringContents(Charset.defaultCharset(), content);
    }
}
