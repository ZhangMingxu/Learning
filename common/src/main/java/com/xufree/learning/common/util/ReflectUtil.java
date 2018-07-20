package com.xufree.learning.common.util;

import java.lang.reflect.Method;

public class ReflectUtil {
    public static void setter(Object obj, String att, Object value, Class<?> type) {
        try {
            Method setter = obj.getClass().getMethod("set"+initStr(att),type);
            setter.invoke(obj,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getter(Object obj, String att, Class<T> type) {
        try {
            Method getter = obj.getClass().getMethod("get"+initStr(att));
            return (T) getter.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String initStr(String att) {
        Character oldFirst = att.charAt(0);
        Character newFirst = Character.toUpperCase(oldFirst);
        return att.replaceFirst(oldFirst.toString(), newFirst.toString());
    }
}
