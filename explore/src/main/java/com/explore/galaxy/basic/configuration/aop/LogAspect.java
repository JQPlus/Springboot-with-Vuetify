package com.explore.galaxy.basic.configuration.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

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
   * @param ex Exception
   * @description: 将异常写入到log
  */
    @AfterThrowing(pointcut = "webLog()", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable {
        LOGGER.error("****LoggingAspect.logAfterThrowingAllMethods() " + ex);
    }
//
//    @AfterReturning("webLog()")
//    public void afterReturning() {
//        LOGGER.info("afterReturning");
//    }
}
