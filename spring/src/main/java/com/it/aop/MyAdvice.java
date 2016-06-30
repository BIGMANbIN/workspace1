package com.it.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
*Aop通知类
*/
public class MyAdvice {

    /**
     * 前置通知
     */

    public void beforeAdvice(){
        System.out.println("前置通知。。。。。。");
    }

    /**
     * 后置通知
     */
    public void afterReturningAdvice(Object result){
        System.out.println("后置通知。。。。。。");

    }

    /**
     * 异常通知
     */
    public void exceptionAdvice(Exception e){
        System.out.println("异常通知。。。"+ e.getMessage());
    }

    /**
     *最终通知
     */
    public void finallyAdvice(){
        System.out.println("最终通知。。。。");
    }


    /**
     * 环绕通知
     */
    public Object AroundAdvice(ProceedingJoinPoint joinPoint) {

        Object object = null;
        try{
            System.out.println("--前置通知--");
            object = joinPoint.proceed();//代表了目标对象方法的执行
            System.out.println("--后置通知--");
        }catch (Throwable throwable){
            throwable.printStackTrace();
            System.out.println("--异常通知--");
        }finally {
            System.out.println("--最终通知--");
        }
        return object;
    }
}

