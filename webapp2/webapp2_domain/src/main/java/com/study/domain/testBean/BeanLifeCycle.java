package com.study.domain.testBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class BeanLifeCycle implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean,
		DisposableBean, SmartLifecycle, BeanPostProcessor {

	private boolean isRunning = false;

	@Override
	public void setBeanName(String name) {
		System.out.println("========== 执行BeanNameAware接口");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("========== 执行BeanFactoryAware接口");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("========== 执行ApplicationContextAware接口");
	}

	@PostConstruct
	public void initBean() {
		System.out.println("========== 执行@PostConstruct注解");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("========== 执行InitializingBean接口");
	}

	@PreDestroy
	public void destroyBean() {
		System.out.println("========== 执行@PreDestroy注解");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("========== 执行DisposableBean接口");
	}

	@Override
	public void start() {
		System.out.println("========== 执行SmartLifecycle接口start方法");
		isRunning = true;
	}

	@Override
	public void stop() {
		System.out.println("========== 执行SmartLifecycle接口stop方法");
		isRunning = false;
	}

	/**
	 * 返回true时start方法会被自动执行，返回false则不会
	 */
	@Override
	public boolean isAutoStartup() {
		System.out.println("========== 执行SmartLifecycle接口isAutoStartup方法");
		return true;
	}

	/**
	 * 1. 只有该方法返回false时，start方法才会被执行。<br/>
	 * 2. 只有该方法返回true时，stop(Runnable callback)或stop()方法才会被执行。
	 */
	@Override
	public boolean isRunning() {
		System.out.println("========== 执行SmartLifecycle接口isRunning方法");
		return isRunning;
	}

	/**
	 * 如果工程中有多个实现接口SmartLifecycle的类，则这些类的start的执行顺序按getPhase方法返回值从小到大执行。<br/>
	 * 例如：1比2先执行，-1比0先执行。 stop方法的执行顺序则相反，getPhase返回值较大类的stop方法先被调用，小的后被调用。
	 */
	@Override
	public int getPhase() {
		return 0;
	}

	@Override
	public void stop(Runnable callback) {
		System.out.println("========== 执行SmartLifecycle接口stop(Runnable callback)方法");
		callback.run();
		isRunning = false;
	}

	/**
	 * 实例化Bean之前
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if ("threadPool".equals(beanName)) {
			System.out.println("========== 执行postProcessBeforeInitialization方法" + beanName);
		}
		return bean;
	}

	/**
	 * 实例化Bean之后
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if ("threadPool".equals(beanName)) {
			System.out.println("========== 执行postProcessAfterInitialization方法" + beanName);
		}
		return bean;
	}

}
