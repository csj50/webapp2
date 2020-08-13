package com.study.domain.testBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanLifeCycle
		implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

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

}
