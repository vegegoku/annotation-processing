package com.progressoft.annotation.processor;

import com.progressoft.annotation.processor.copier.WithCopier;

@WithCopier(mode = WithCopier.Mode.PUBLIC_MEMEBERS)
public class CustomerProfile implements Cloneable{

    public int id;
    public String name;
    public String account;

    public CustomerProfile() {
    }

    @Override
    protected CustomerProfile clone() throws CloneNotSupportedException {
        return new CustomerProfileCopier().copy(this);
    }
}
