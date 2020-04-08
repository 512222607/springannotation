package com.atguigu.test;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import com.atguigu.config.MainConfig2;
import com.atguigu.tx.TxConfig;
import com.atguigu.tx.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @author zhangzm
 * @date 2019/7/8 16:00
 */
public class IOCTest_tx {

	AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);


	@Test
	public void test01() {
		AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(TxConfig.class);
		UserService bean = anno.getBean(UserService.class);
		bean.inserUser();
	}

	private void printBeans(AnnotationConfigApplicationContext anno) {
		String[] beanDefinitionNames = anno.getBeanDefinitionNames();
		for (String s :
				beanDefinitionNames) {
			System.out.println(s);
		}
	}
}
