package com.xufree.learning.java.test;

import com.xufree.learning.java.reflect.CompareFields;
import org.junit.Test;

import java.math.BigDecimal;

public class CompareFieldsTest {
    @Test
    public void CompareFieldsTest() {

        System.out.println(CompareFields.compareFields(
                new TestPojo(
                        new BigDecimal("1.0"),
                        "asdasd",
                        1324524,
                        12312L),
                new TestPojo(
                        new BigDecimal("1"),
                        "asdasd",
                        1324524,
                        12312L)));
    }

    @Test
    public void test() {
        System.out.println("a".toUpperCase());
        System.out.println(new BigDecimal("1").equals(new BigDecimal("1.0")));
    }
}
