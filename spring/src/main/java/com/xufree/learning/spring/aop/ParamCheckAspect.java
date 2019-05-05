package com.xufree.learning.spring.aop;

import com.xufree.learning.spring.annotation.ParamConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author zhangmingxu ON 11:24 2019-05-05
 **/
@Component
@Aspect
public class ParamCheckAspect {

    @Around(value = "execution(* com.xufree.learning.spring.beans.*.*(..))" )
    public Object checkParam(ProceedingJoinPoint pjp) throws Throwable {
        check(pjp);
        return pjp.proceed();
    }

    private void check(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        if (args == null) {
            return;
        }
        Method method = getMethod(pjp);
        if (method == null) {
            return;
        }
        Parameter[] parameters = method.getParameters();
        if (parameters == null || parameters.length == 0) {
            return;
        }
        for (int i = 0; i < parameters.length; i++) {
            check(parameters[i], args[i]);
        }
    }

    private void check(Parameter param, Object value) {
        ParamConfig paramConfig = param.getAnnotation(ParamConfig.class);
        if (paramConfig != null && paramConfig.required() && value == null) {
            throw new IllegalArgumentException(paramConfig.name() + " can not be null");
        }
    }

    private Method getMethod(ProceedingJoinPoint pjp) {
        String methodName = pjp.getSignature().getName();
        Method[] methods = pjp.getTarget().getClass().getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return method;
            }
        }
        return null;
    }
}
