package com.progressoft.jfw.annotations.processor.test;

import com.google.common.truth.Truth;
import com.google.testing.compile.CompileTester;
import com.google.testing.compile.JavaFileObjects;

import javax.annotation.processing.Processor;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import java.nio.charset.Charset;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class TargetProcessor{

    private final String inputClassName;
    private final Processor processor;
    private CompileTester.SuccessfulCompilationClause successfulCompilationClause;

    public TargetProcessor(String inputClassName, Processor processor) {
        this.inputClassName = inputClassName;
        this.processor = processor;
    }

    public void generates(String result, String... rest){
        if(rest !=null && rest.length>0){
            JavaFileObject[] files=new JavaFileObject[rest.length];
            for(int i=0;i<rest.length;i++){
                files[i]=JavaFileObjects.forSourceString("",rest[i]);
            }
            compilesWithoutErrors()
                    .and()
                    .generatesSources(JavaFileObjects.forSourceString("",result), files);
        }else{
            compilesWithoutErrors()
                    .and()
                    .generatesSources(JavaFileObjects.forSourceString("",result));
        }

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
