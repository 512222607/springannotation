package com.atguigu.test;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhangzm
 * @date 2019/7/8 15:44
 */
public class MainTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		Object person = annotationConfigApplicationContext.getBean("person");
		System.out.println(person);
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String s : beanDefinitionNames) {
			System.out.println(s);
		}
		String[] beanNamesForType = annotationConfigApplicationContext.getBeanNamesForType(Person.class);
		for (String name : beanNamesForType) {
			System.out.println(name);
		}
	}


}
