package org.sen.modules.dictionary.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {


    private final String POINT_CUT = "@within(org.sen.modules.dictionary.aop.annotation.RequestAopLog)";

    private long _ElapsedTime;

    @Pointcut(POINT_CUT)
    public void pointCut(){}


    /**
     * 前置
     */
    @Before("pointCut()")
    public void xBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String classType = joinPoint.getTarget().getClass().getName();
        log.info("{}.{} 入参:{}",classType,methodName,joinPoint.getArgs()[0]);
    }
    /**
     * 环绕
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object xAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String classType = joinPoint.getTarget().getClass().getName();
        log.info("==={}.{}=== 请求调用开始:",classType,methodName);
        long _BngTime = System.currentTimeMillis();
        Object o = joinPoint.proceed();
        _ElapsedTime = System.currentTimeMillis() - _BngTime;
        log.info("==={}.{}=== 请求调用结束,总耗时{}ms:",classType,methodName,_ElapsedTime);
        return o;
    }
    /**
     * 后置
     *
     * @param joinPoint
     */
    @AfterReturning(pointcut = ("pointCut()"))
    public void xAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String classType = joinPoint.getTarget().getClass().getName();
        log.info("{}.{} 出参:{}",classType,methodName,joinPoint.getArgs()[0]);

    }
    /**
     * 例外
     *
     * @param joinPoint
     */
    @AfterThrowing(pointcut="pointCut()")
    public void xException(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String classType = joinPoint.getTarget().getClass().getName();
        log.error("{}.{} 异常:{}",classType,methodName, "1");
    }
}
