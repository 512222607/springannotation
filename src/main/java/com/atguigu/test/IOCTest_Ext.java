package com.atguigu.test;

import com.atguigu.config.MainConfig2;
import com.atguigu.ext.ExtConfig;
import com.atguigu.tx.TxConfig;
import com.atguigu.tx.UserService;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhangzm
 * @date 2019/7/8 16:00
 */
public class IOCTest_Ext {

	@Test
	public void test01() {
		AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(ExtConfig.class);
		anno.publishEvent(new ApplicationEvent("我发布了一个事件") {
		});
		anno.close();
	}

//	private void printBeans(AnnotationConfigApplicationContext anno) {
//		String[] beanDefinitionNames = anno.getBeanDefinitionNames();
//		for (String s :
//				beanDefinitionNames) {
//			System.out.println(s);
//		}
//	}
}
