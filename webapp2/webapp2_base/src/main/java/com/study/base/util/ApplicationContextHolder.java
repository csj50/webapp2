package com.study.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 手动获取spring中的Bean
 * 
 * @author User
 *
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	public static <T> T getBean(String name, Class<T> clazz) {
		T bean = getApplicationContext().getBean(name, clazz);
		if (bean == null) {
			throw new IllegalStateException("bean factory is null");
		}
		return bean;
	}

	public static Object getBean(String name, Object... args) {
		return getApplicationContext().getBean(name, args);
	}

	public static <T> T getBean(Class<T> clazz) {
		T bean = getApplicationContext().getBean(clazz);
		if (bean == null) {
			throw new IllegalStateException("bean factory is null");
		}
		return bean;
	}

	public static <T> T getBean(Class<T> clazz, Object... args) {
		T bean = getApplicationContext().getBean(clazz, args);
		if (bean == null) {
			throw new IllegalStateException("bean factory is null");
		}
		return bean;
	}

	public static ApplicationContext getApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicationContext is not init.");
		}
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextHolder.applicationContext = applicationContext;
	}

	public static void cleanHolder() {
		applicationContext = null;
	}
}
