package com.xufree.learning.common.annotation.aop;

import com.xufree.learning.common.annotation.TimeStatistics;
import com.xufree.learning.common.util.SleepUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TimeStatisticsAnnotationHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Around("@annotation(com.xufree.learning.common.annotation.TimeStatistics)")
    public Object execute(ProceedingJoinPoint pjp) {
        String targetMethod = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
        Object result = null;
        try {
            long begin = now();
            result = pjp.proceed();
            long cost = now() - begin;
            logger.info("方法{}耗时{}", targetMethod, cost);
        } catch (Throwable throwable) {
            logger.error("自动统计方法耗时异常", throwable);
        }
        return result;
    }

    private long now() {
        return System.currentTimeMillis();
    }
}
