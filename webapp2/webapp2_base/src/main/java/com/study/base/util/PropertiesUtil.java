package com.study.base.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertiesUtil extends PropertyPlaceholderConfigurer {

	private static Map<String, String> properties = new HashMap<>();

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);

		for (String key : props.stringPropertyNames()) {
			properties.put(key, props.getProperty(key));
		}

	}

	public static String getProperties(String key) {
		return properties.get(key);
	}

}
