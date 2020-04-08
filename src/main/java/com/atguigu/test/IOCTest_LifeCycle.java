package com.atguigu.test;

import com.atguigu.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhangzm
 * @date 2020/2/14 22:24
 */
public class IOCTest_LifeCycle {

	@Test
	public void test01() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
		System.out.println("容器创建完成");

		System.out.println("获取对象");
		applicationContext.getBean("car");

		applicationContext.close();
		System.out.println("容器关闭完成");
	}

	@Test
	public void testCat() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
		System.out.println("容器创建完成");

		applicationContext.close();
		System.out.println("容器关闭完成");
	}
}
