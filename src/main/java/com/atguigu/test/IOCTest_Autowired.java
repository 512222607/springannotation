package com.atguigu.test;

import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.bean.Person;
import com.atguigu.config.MainConfigOfAutowired;
import com.atguigu.config.MainConfigOfPropertyValues;
import com.atguigu.dao.BookDao;
import com.atguigu.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author zhangzm
 * @date 2020/2/14 22:24
 */

/**
 */
public class IOCTest_Autowired {

	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

	@Test
	public void test01() {
		BookService bookService = (BookService) applicationContext.getBean("bookService");
		System.out.println(bookService);

		BookDao bookDao = (BookDao) applicationContext.getBean(BookDao.class);

		System.out.println(bookDao);
	}

	@Test
	public void test02() {
		Boss boss = (Boss) applicationContext.getBean(Boss.class);
		Car car = (Car) applicationContext.getBean("car");
		Color color = (Color) applicationContext.getBean("color");
		System.out.println(boss);
		System.out.println(car);
		System.out.println(color);
		System.out.println(boss.getCar() == car);
		System.out.println(color.getCar() == car);
	}
}
