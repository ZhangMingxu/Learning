package com.xufree.learning.java.agent.transformer;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 统计方法执行时间
 *
 * @author zhangmingxu ON 15:35 2019-06-28
 **/
public class RunTimeTransformer implements ClassFileTransformer {
    private ClassPool classPool = ClassPool.getDefault();

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        CtClass ctClass = null;
        byte[] returnBytes = null;
        try {
            ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
            if (!ctClass.isInterface()) {
                for (CtBehavior behavior : ctClass.getDeclaredBehaviors()) {
                    try {
                        behavior.addLocalVariable("start", CtClass.longType);
                        behavior.insertBefore("start = System.currentTimeMillis();");
//                        behavior.insertAfter("System.out.println("+behavior.getName()+"+\" use: \"+(System.currentTimeMillis()-start));");
                        behavior.insertAfter("System.out.println(\"leave " + behavior.getName() + " and time is :\" + (System.currentTimeMillis() - start));");
                    } catch (CannotCompileException e) {
                        e.printStackTrace();
                    }
                }
            }
            returnBytes = ctClass.toBytecode();
        } catch (IOException | CannotCompileException e) {
            e.printStackTrace();
        } finally {
            if (ctClass != null) {
                ctClass.detach();
            }
        }
        return returnBytes;
    }
}
