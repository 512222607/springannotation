package com.atguigu.test;

import com.atguigu.aop.MathCalculator;
import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import com.atguigu.config.MainConfig2;
import com.atguigu.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @author zhangzm
 * @date 2019/7/8 16:00
 */
public class IOCTest_AOP {

	AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);


	@Test
	public void test01() {
		MathCalculator bean = annotationConfigApplicationContext.getBean(MathCalculator.class);
		System.out.println(bean.div(1, 1));
	}

	private void printBeans(AnnotationConfigApplicationContext anno) {
		String[] beanDefinitionNames = anno.getBeanDefinitionNames();
		for (String s :
				beanDefinitionNames) {
			System.out.println(s);
		}
	}
}
