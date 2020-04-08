package com.atguigu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zhangzm
 * @date 2020/2/17 23:10
 */

/**
 * 后置处理器，在初始化方法的前后进行处理工作
 * 将后置处理器加入到容器
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor初始化方法前调用..."+beanName+"==>"+bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor初始化方法后调用..."+beanName+"==>"+bean);
		return bean;
	}
}
