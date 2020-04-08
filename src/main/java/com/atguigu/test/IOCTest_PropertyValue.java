package com.atguigu.test;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfigOfLifeCycle;
import com.atguigu.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author zhangzm
 * @date 2020/2/14 22:24
 */

/**
 * <p><b1>属性赋值的方式</b1><br>
 * 1、在对象的属性上面使用@Value注解进行赋值<br>
 *     注解中可以放的值有以下几种写法：
 *     		1-基本数值
 *     		2-可以写SpEL，#{}的方式
 *     		3-可以写${},取出配置文件中的值（以及在运行环境变量environment里面的值）
 * </p>
 */
public class IOCTest_PropertyValue {

	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

	@Test
	public void test01() {
		printBeans(applicationContext);
		System.out.println("---------------------");

		Person person = (Person) applicationContext.getBean("person");
		System.out.println(person);

		System.out.println("----------从环境变量中获取配置文件的信息(使用propertySource读取配置文件后)--------");
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		System.out.println(environment.getProperty("person.nickName"));
		applicationContext.close();
	}

	protected void printBeans(AnnotationConfigApplicationContext applicationContext) {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}
}
