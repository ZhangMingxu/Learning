package com.xufree.learning.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于标明在更新权益时哪些属性发生改变后会触发重新审核 现在用于Asset和AssetVendorSku这两个类的属性上
 * 被该注解注明的属性在更新时如果发生修改会触发重新审核
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReviewIfUpdate {
}
