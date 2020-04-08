package com.atguigu.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zhangzm
 * @date 2019/7/8 17:22
 */
public class WindowsCondition implements Condition {
	/**
	 * @param conditionContext 判断条件能使用的上下文
	 * @param annotatedTypeMetadata 注释信息
	 * @return
	 */
	public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
		// 获取到ioc使用的beanfactory
		ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();

		// 获取类加载器
		ClassLoader classLoader = conditionContext.getClassLoader();

		// 获取环境信息(包括环境变量，虚拟机信息等)
		Environment environment = conditionContext.getEnvironment();

		// 获取到bean定义的注册类，所有的bean都在BeanDefinitionRegistry中定义注册
		BeanDefinitionRegistry registry = conditionContext.getRegistry();

		String property = environment.getProperty("os.name");

		if (property.contains("Windows")) {
			return true;
		}
		return false;
	}
}
