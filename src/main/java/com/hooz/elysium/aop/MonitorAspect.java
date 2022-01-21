package com.hooz.elysium.aop;

import com.hooz.elysium.core.TypedParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Method Monitor Aspect
 *
 * @author Kidd Zhou
 * @date 2022-01-20
 */
@Component
@Aspect
public class MonitorAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorAspect.class);

    @Pointcut("@annotation(com.hooz.elysium.annotation.Monitor)")
    public void methodPointcut() {}

    @Before("methodPointcut()")
    public void before() {}

    @Around("methodPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long t0 = System.currentTimeMillis();
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String[] paramNames = methodSignature.getParameterNames();
        Class<?>[] paramTypes = methodSignature.getParameterTypes();
        int size = args != null ? args.length : 0;
        List<TypedParam> params = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            TypedParam p = new TypedParam(paramTypes[i], paramNames[i], args[i]);
            params.add(p);
        }
        Object result = pjp.proceed();
        TypedParam r = new TypedParam((Class<?>) methodSignature.getReturnType(), "", result);
        long t1 = System.currentTimeMillis();
        LOGGER.debug("[Elysium Monitor]: {}.{}(), params: {}, return: {}, execution time: {}ms", className, methodName, params, r, t1 - t0);
        return result;
    }

    @After("methodPointcut()")
    public void after() {}

    @AfterReturning("methodPointcut()")
    public void afterReturning() {}

    @AfterThrowing(value = "methodPointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint jp, Throwable ex) {
        String className = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        LOGGER.error("[Elysium Monitor]: {}.{}() exception msg:", className, methodName, ex);
    }

}
