package com.xufree.learning.java.test;

import com.xufree.learning.java.annotation.ReviewIfUpdate;

import java.math.BigDecimal;

public class TestPojo {
    @ReviewIfUpdate
    private BigDecimal bigDecimal;
    @ReviewIfUpdate
    private String string;
    @ReviewIfUpdate
    private Integer integer;
    private Long testLong;

    TestPojo(BigDecimal bigDecimal, String string, Integer integer, Long testLong) {
        this.bigDecimal = bigDecimal;
        this.string = string;
        this.integer = integer;
        this.testLong = testLong;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }
    

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public String getString() {
        return string;
    }

    public Integer getInteger() {
        return integer;
    }

    public Long getTestLong() {
        return testLong;
    }

    public void setTestLong(Long testLong) {
        this.testLong = testLong;
    }
}