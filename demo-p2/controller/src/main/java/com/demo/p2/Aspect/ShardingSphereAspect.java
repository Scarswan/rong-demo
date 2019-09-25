package com.demo.p2.Aspect;

import org.apache.shardingsphere.api.hint.HintManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ShardingSphereAspect {

    @Pointcut("@annotation(com.demo.p2.annotation.ShardingSphereMaster)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 方法执行前
        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();

        // 执行方法，获取原始返回值
        Object object =  point.proceed();

        // 方法执行后
        hintManager.close();

        return object;
    }

}
