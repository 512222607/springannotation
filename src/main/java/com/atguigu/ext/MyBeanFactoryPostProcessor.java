package com.atguigu.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author zhangzm
 * @date 2020/3/18 22:40
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanFactoryPostProcessor...执行了...postProcessBeanFactory");
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
		System.out.println("当前BeanFactory中有"+beanDefinitionCount+"个bean");
		System.out.println(Arrays.asList(beanDefinitionNames));
	}
}
