package com.progressoft.jfw.annotations.processor.copier;

import com.progressoft.jfw.annotations.copier.*;

@WithCopier(mode = WithCopier.Mode.PUBLIC_MEMEBERS)
public class AnnotatedClassWithIgnoredFieldUsingPublicFields {

    @IgnoreCopy
    public String stringValue;
    public int integerValue;
    public Double doubelValue;
    public float floatValue;
    public boolean boolValue;
    public Boolean boolWraperValue;

}
