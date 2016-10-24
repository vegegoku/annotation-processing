# Annotation-processing
a set of annotation processors and utilities for annotation processing development and testing

# Copier annotation
An annotation with annotation processor to generate copier classes for datastructures

Copier annotation example :

the following class :

```java

package com.progressoft.annotation.processor;

@WithCopier
public class AnnotatedClassWithSingleField {

    private int integerField;

    public int getIntegerField() {
        return integerField;
    }

    public void setIntegerField(int integerField) {
        this.integerField = integerField;
    }
}
```

will generate the following copier class :

```java
package com.progressoft.annotation.processor;

class AnnotatedClassWithSingleFieldCopier {

    AnnotatedClassWithSingleField copy(AnnotatedClassWithSingleField original) throws CloneNotSupportedException {
        AnnotatedClassWithSingleField result=new AnnotatedClassWithSingleField();
        result.setIntegerField(original.getIntegerField());
        return result;
    }
}
```

for more examples please refer to the unit test included in the annotation processor project.

#Builder annotation:
an annotation with annotation processor to generate a builder class for the annotated class:

Builder annotation sample :
the following class :
```java
package com.progressoft.annotation.processor.builder;

@WithBuilder
public class AnnotatedClassWithManyFields {

    private Integer integerValue;
    private int intValue;
    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;

    public AnnotatedClassWithManyFields(Integer integerValue, int intValue, String stringValue, Boolean booleanValue, boolean boolValue, Double doubleValue, double dValue) {
        this.integerValue = integerValue;
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.booleanValue = booleanValue;
        this.boolValue = boolValue;
        this.doubleValue = doubleValue;
        this.dValue = dValue;
    }
}

```
will generate the following builder class :

```java
package com.progressoft.annotation.processor.builder;

class AnnotatedClassWithManyFieldsBuilder {

    private Integer integerValue;
    private int intValue;
    private String stringValue;
    private Boolean booleanValue;
    private boolean boolValue;
    private Double doubleValue;
    private double dValue;

    AnnotatedClassWithManyFields build() {
        return new AnnotatedClassWithManyFields(integerValue, intValue, stringValue, booleanValue, boolValue, doubleValue, dValue);
    }

    AnnotatedClassWithManyFieldsBuilder integerValue(Integer integerValue) {
        this.integerValue = integerValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder intValue(int intValue) {
        this.intValue = intValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder stringValue(String stringValue) {
        this.stringValue = stringValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder booleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder boolValue(boolean boolValue) {
        this.boolValue = boolValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder doubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
        return this;
    }

    AnnotatedClassWithManyFieldsBuilder dValue(double dValue) {
        this.dValue = dValue;
        return this;
    }

}

```
for more sample please refer to the unit tests available in the builder project.

#Processor testing :

processor-test project contains the required dependencies to test an annotation processor plus a wrapper i wrote to make the testing easier.

general syntax

```java
assertProcessing("input source file full path goes here")
                .withProcessor(processor instance goes here)
                .generates("expected generated source string goes here");

```
sample test case

```java
    @Test
    public void givenAClassAnnotatedAsWithCopier_shouldGenerateAClassWithSameNameButEndsWithCopier() throws Exception {
        assertProcessing("com/progressoft/annotation/processor/AnnotatedClassWithSingleField.java")
                .withProcessor(new CopierAnnotationProcessor())
                .generates(getExpectedResultFileContent("AnnotatedClassWithSingleFieldCopier.java"));
     }
```
for more testing examples please refer to the copier annotation processor project.

Enjoy :-)

This is editted by Rafat.
Re-edited by Bawaneh.
