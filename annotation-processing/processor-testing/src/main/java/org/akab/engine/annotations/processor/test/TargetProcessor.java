package org.akab.engine.annotations.processor.test;

import com.google.common.truth.Truth;
import com.google.testing.compile.CompileTester;
import com.google.testing.compile.JavaFileObjects;

import javax.annotation.processing.Processor;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class TargetProcessor {

    private final String inputClassName;
    private final Processor processor;
    private CompileTester.SuccessfulCompilationClause successfulCompilationClause;

    public TargetProcessor(String inputClassName, Processor processor) {
        this.inputClassName = inputClassName;
        this.processor = processor;
    }

    public void generates(String result, String... rest) {
        compilesWithoutErrors()
                .and()
                .generatesSources(JavaFileObjects.forSourceString("", result), asJavaFileObjectsArray(rest));

    }

    private JavaFileObject[] asJavaFileObjectsArray(String... rest) {
        JavaFileObject[] result = new JavaFileObject[rest.length];
        for (int i = 0; i < rest.length; i++)
            result[i] = JavaFileObjects.forSourceString("", rest[i]);
        return result;
    }

    public CompileTester.SuccessfulCompilationClause compilesWithoutErrors() {
        return Truth.assert_().about(javaSource()).that(JavaFileObjects.forResource(inputClassName))
                .processedWith(processor).compilesWithoutError();
    }

    public void generatesResource(String basePackage, String fileName, String content) {
        compilesWithoutErrors()
                .and()
                .generatesFileNamed(StandardLocation.SOURCE_OUTPUT, basePackage, fileName).withStringContents(Charset.defaultCharset(), content);
    }
}
