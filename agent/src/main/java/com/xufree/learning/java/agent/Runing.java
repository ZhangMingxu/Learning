package com.xufree.learning.java.agent;

import com.xufree.learning.java.agent.transformer.RunTimeTransformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;


/**
 * @author zhangmingxu ON 15:21 2019-06-28
 **/
public class Runing {
    public static void premain(String args, Instrumentation instrumentation) {
        if (args == null || args.length() == 0) {
            System.err.println("prefix is null");
            return;
        }
        System.out.println("prefix is " + args);
        instrumentation.addTransformer(new RunTimeTransformer());
        Class[] classes = instrumentation.getAllLoadedClasses();
        for (Class aClass : classes) {
            handleClass(args, aClass, instrumentation);
        }
    }

    private static void handleClass(String prefix, Class clazz, Instrumentation instrumentation) {
        if (matchClass(prefix, clazz)) {
            try {
                instrumentation.retransformClasses(clazz);
            } catch (UnmodifiableClassException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean matchClass(String prefix, Class clazz) {
        String className = clazz.getName();
        return className.startsWith(prefix);
    }
}
