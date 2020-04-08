package com.atguigu.test;

import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.config.MainConfigOfAutowired;
import com.atguigu.config.MainConfigOfProfile;
import com.atguigu.dao.BookDao;
import com.atguigu.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhangzm
 * @date 2020/2/14 22:24
 */

/**
 */
public class IOCTest_Profile {

	@Test
	public void test() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
		String[] beanDefinitionNames = applicationContext.getBeanNamesForType(Color.class);
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
		applicationContext.close();
	}

	@Test
	public void test01() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.getEnvironment().setActiveProfiles("dev");

		applicationContext.register(MainConfigOfProfile.class);

		applicationContext.refresh();

		String[] beanDefinitionNames = applicationContext.getBeanNamesForType(Color.class);
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
		applicationContext.close();
	}
}
