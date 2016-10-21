package com.progressoft.annotation.processor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuilderClientTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void nothing() throws Exception {
        System.out.println(new XyzBuilder(10, 10).booleanValue(true).boolValue(false).doubleValue(5d).dValue(7d).stringValue("jjjjd").build().toString());
    }
}