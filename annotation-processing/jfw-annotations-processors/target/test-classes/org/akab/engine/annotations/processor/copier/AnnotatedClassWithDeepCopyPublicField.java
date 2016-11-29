package org.akab.engine.annotations.processor.copier;

import org.akab.engine.annotations.copier.DeepCopy;
import org.akab.engine.annotations.copier.WithCopier;
import org.akab.engine.annotations.processor.copier.AnnotatedClassWithDeepCopyPublicFieldCopier;

@WithCopier(mode = WithCopier.Mode.PUBLIC_MEMEBERS)
public class AnnotatedClassWithDeepCopyPublicField {

    public class AnotherObject{
        @Override
        protected AnotherObject clone() throws CloneNotSupportedException {
            return new AnotherObject();
        }
    }

    public String stringValue;
    public int integerValue;
    public Double doubelValue;

    @DeepCopy
    public AnotherObject anotherObject;

    @Override
    protected AnnotatedClassWithDeepCopyPublicField clone() throws CloneNotSupportedException {
        return new AnnotatedClassWithDeepCopyPublicFieldCopier().copy(this);
    }
}
