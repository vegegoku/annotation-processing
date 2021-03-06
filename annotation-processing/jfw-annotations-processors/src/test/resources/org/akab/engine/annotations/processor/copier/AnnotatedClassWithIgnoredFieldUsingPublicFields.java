package org.akab.engine.annotations.processor.copier;

import org.akab.engine.annotations.copier.IgnoreCopy;
import org.akab.engine.annotations.copier.WithCopier;

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
