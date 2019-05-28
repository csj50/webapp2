package com.study.util;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectInvokeUtil {
	private static final Logger logger = LoggerFactory.getLogger(ReflectInvokeUtil.class);

	/**
	 * 用反射的方式调用方法
	 *
	 * @param className
	 *            类名称
	 * @param methodName
	 *            方法名称
	 * @param methodParam
	 *            方法参数
	 * @return 返回值
	 */
	public static Object invokeMethod(String className, String methodName, Object methodParam) throws Exception {
		Object result;
		try {
			Class clazz = Class.forName(className);
			Object bean = ApplicationContextHolder.getBean(className);
			if (methodParam == null) {
				result = clazz.getDeclaredMethod(methodName).invoke(bean);
			} else {
				result = clazz.getDeclaredMethod(methodName, Object.class).invoke(bean, methodParam);
			}
			return result;
		} catch (InvocationTargetException e) {
			// Java异常处理 InvocationTargetException（反射异常）
			// InvocationTargetException异常由Method.invoke(obj,
			// args...)方法抛出。当被调用的方法的内部抛出了异常而没有被捕获时，将由此异常接收！！！
			Throwable t = e.getTargetException();// 获取目标异常
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}
