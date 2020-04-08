package com.atguigu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * @author zhangzm
 * @date 2020/2/24 22:42
 */
@Aspect
public class LogAspect {

	//抽取公共的切入点表达式
	//1、在本类应用 写法如before方法中的写法
	//2、在外部类应用，或在其他切面类中引用，写法如after
	@Pointcut("execution(public int com.atguigu.aop.MathCalculator.*(..))")
	public void pointCut(){}

	//在目标方法之前切入，括号中是切入点表达式（指定在哪个方法切入）
	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName()+"运行。。参数列表是，{"+ Arrays.asList(joinPoint.getArgs())+"}");
	}

	@After("com.atguigu.aop.LogAspect.pointCut()")
	public void logEnd(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName()+"结束。。");
	}

	@AfterReturning(value = "pointCut()",returning = "result")
	//joinpoint只能放在第一个参数
	public void logReturn(JoinPoint joinPoint,Object result) {
		System.out.println(joinPoint.getSignature().getName()+"正常返回。。运算结果，{"+result+"}");
	}

	@AfterThrowing(value = "pointCut()",throwing = "exception")
	public void logException(JoinPoint joinPoint,Exception exception) {
		System.out.println(joinPoint.getSignature().getName()+"异常。。异常信息，{"+exception+"}");
	}
}
