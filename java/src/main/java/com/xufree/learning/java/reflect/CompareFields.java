package com.xufree.learning.java.reflect;

import com.xufree.learning.java.annotation.ReviewIfUpdate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import static java.util.Locale.ENGLISH;

public class CompareFields {
    public static boolean compareFields(Object oldOne, Object newOne) {
        Class type = oldOne.getClass();
        Field[] assetFields = type.getDeclaredFields();
        for (Field field : assetFields) {
            if (field.getAnnotation(ReviewIfUpdate.class) != null) {
                try {
                    Method readMethod = type.getDeclaredMethod("get"+ capitalize(field.getName()));
                    if (field.getType() == BigDecimal.class) {
                        BigDecimal oldValue = (BigDecimal) readMethod.invoke(oldOne);
                        BigDecimal newValue = (BigDecimal) readMethod.invoke(newOne);
                        if (oldValue.compareTo(newValue) != 0){
                            return true;
                        }
                    } else {
                        Object oldValue = readMethod.invoke(oldOne);
                        Object newValue = readMethod.invoke(newOne);
                        if (!oldValue.equals(newValue)){
                            return true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    private static String capitalize(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        return name.substring(0, 1).toUpperCase(ENGLISH) + name.substring(1);
    }
}
