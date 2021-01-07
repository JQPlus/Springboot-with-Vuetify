package com.explore.galaxy.basic.configuration.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Date;

@Component
@Aspect
public class LogAspect {


    private static final Logger LOGGER = LogManager.getLogger(LogAspect.class);


    /**
     * @description: 定义一个切点
     */
    @Pointcut("execution(public * com.explore.galaxy.basic.modules.*..service..*(..)))")
    public void webLog() {
    }

    /**
     * @param proceedingJoinPoint: 仅支持@Around
     * @description: 被拦截器拦截的方法的AOP表达式
     */
    @Around("webLog()")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        LOGGER.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
        return result;
    }

    //
//    @Before("webLog()")
//    public void doBefore() {
//        LOGGER.info("doBefore");
//    }
//
//    @After("webLog()")
//    public void doAfter() {
//        LOGGER.info("doAfter");
//    }

    /**
     * @description: 以下两种情况会进到这里
     * 1.业务逻辑没有try-catch，但是报错
     * 2.catch中throw了新的运行时异常
     * 因为事务是基于AOP实现的，所以事务也具有相同的特点，在需要事务回滚的地方都需要加上@Transaction注解
     * 手动事务 => TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
     */
    @AfterThrowing(pointcut = "webLog()", throwing = "error")
    public void logAfterThrowingAllMethods(JoinPoint jp, Throwable error) {
        LOGGER.error("***Exception Method Signature: " + jp.getSignature()+"has error as belows:" + error);
    }
//
//    @AfterReturning("webLog()")
//    public void afterReturning() {
//        LOGGER.info("afterReturning");
//    }
}
