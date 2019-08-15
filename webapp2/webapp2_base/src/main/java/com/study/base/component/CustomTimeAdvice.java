package com.study.base.component;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.study.base.annotation.TimeCount;
import com.study.base.util.ApplicationContextHolder;

/**
 * 自定义切面
 * @author User
 *
 */
@Aspect //切面声明
@Component //组件声明
@Order(1) //切面执行顺序
public class CustomTimeAdvice {

	public static Logger logger = LoggerFactory.getLogger(CustomTimeAdvice.class);
	
	/**
	 * 切入点为添加了@TimeCount注解的方法
	 */
	@Pointcut(value = "@annotation(com.study.base.annotation.TimeCount)")
	public void pointCut() {
		
	}
	
	/**
	 * @param point 被切面方法相关属性
	 * @param timeCount 注解类对象
	 */
	@Around(value = "pointCut() && @annotation(timeCount)")
	public void timeAround(ProceedingJoinPoint point, TimeCount timeCount) {
		//获取被切面方法的方法签名
		org.aspectj.lang.Signature funSignature = point.getSignature();
		//返回值的类型
		Class returnType = ((MethodSignature) funSignature).getReturnType();
		//入参
		Object[] objects = point.getArgs();
		
		logger.info("被切面方法名: {}", funSignature.getName());
		logger.info("返回值类型: {}", returnType.getName());
		logger.info("入参: {}", objects);
		
		//业务方法返回值
		Object respBean = null;
		//签名处理类
		DefaultTimeCountHandler defaultTimeCountHandler = null;
		//开始时间
		long start = 0;
		//结束时间
		long end = 0;
		
		try {
			//从spring上下文中获取签名操作对象
			defaultTimeCountHandler = (DefaultTimeCountHandler) ApplicationContextHolder.getBean(timeCount.value());
			
			//===================================前置处理================================
			start = defaultTimeCountHandler.start();
			logger.info("方法开始时间: {}", start);
			
			//===================================原请求处理================================
			try {
				respBean = point.proceed();
			} catch (Throwable throwable) {
				logger.error(throwable.getMessage(), throwable);
			}
			
			//===================================后置处理================================
			end = defaultTimeCountHandler.end();
			logger.info("方法结束时间: {}", end);
			
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			
		} finally {
			//===================================收尾处理================================
			logger.info("方法消耗时间: {}", end - start);
		}
	}
}
