package com.it.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import javax.inject.Named;

/**
 * Aop通知类
 */

@Named
@Aspect
public class MyAdvice {


    @Pointcut("execution(* com.it.dao..*.*(..))")
    public void pointcut() {
    }

    /**
     * 前置通知
     */

    //@Before("pointcut()")
    public void beforeAdvice() {
        System.out.println("前置通知。。。。。。");
    }

    /**
     * 后置通知
     */

    //@AfterReturning(pointcut = "pointcut()",returning = "result")
    public void afterReturningAdvice(Object result) {
        System.out.println("后置通知。。。。。。");

    }

    /**
     * 异常通知
     */
    //@AfterThrowing(pointcut = "pointcut()",throwing = "e")
    public void exceptionAdvice(Exception e) {
        System.out.println("异常通知。。。" + e.getMessage());
    }

    /**
     * 最终通知
     */
    //@After("pointcut()")
    public void finallyAdvice() {
        System.out.println("最终通知。。。。");
    }


    /**
     * 环绕通知
     */

    @Around("pointcut()")
    public Object AroundAdvice(ProceedingJoinPoint joinPoint) {

        Object object = null;
        try {
            System.out.println("--前置通知--");
            object = joinPoint.proceed();//代表了目标对象方法的执行
            System.out.println("--后置通知--");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("--异常通知--");
        } finally {
            System.out.println("--最终通知--");
        }
        return object;
    }
}

