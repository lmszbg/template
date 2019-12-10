package org.sen.modules.dictionary.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {


    private final String POINT_CUT = "execution(public * org.sen.modules.*.controller.*.*(..))";

    @Pointcut(POINT_CUT)
    public void pointCut(){}


    @Before(value = "pointCut()")
    public void controllerBefore(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        log.info(joinPoint.getArgs().toString());
    }
}
