package com.atguigu.condition;

import com.atguigu.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhangzm
 * @date 2020/2/13 22:29
 */
public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		boolean color = registry.containsBeanDefinition("com.atguigu.bean.Color");
		if (color) {
			//指定Bean定义信息：（Bean的类型，作用域）
			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
			//注册一个bean，并指定bean名
			registry.registerBeanDefinition("rainbow",rootBeanDefinition);
		}
	}
}
