package com.atguigu.test;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import com.atguigu.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzm
 * @date 2019/7/8 16:00
 */
public class IOCTest {

	AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);


	@Test
	public void test01() {
		AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] beanDefinitionNames = anno.getBeanDefinitionNames();
		for (String s :
				beanDefinitionNames) {
			System.out.println(s);
		}

	}

	@Test
	public void test02() {

		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String s :
				beanDefinitionNames) {
			System.out.println(s);
		}
		System.out.println("↑调用之前-----------调用之后↓");
		Object person = annotationConfigApplicationContext.getBean("person");
		printBeans(annotationConfigApplicationContext);
	}

	@Test
	public void test03() {

		String[] beanNamesForType = annotationConfigApplicationContext.getBeanNamesForType(Person.class);
		ConfigurableEnvironment environment = annotationConfigApplicationContext.getEnvironment();
		String property = environment.getProperty("os.name");
		System.out.println(property);
		for (String s :
				beanNamesForType) {
			System.out.println(s);
		}

		Map<String, Person> beansOfType = annotationConfigApplicationContext.getBeansOfType(Person.class);
		for (Map.Entry e :
				beansOfType.entrySet()) {
			System.out.println(e);
		}
	}

	@Test
	public void testImport() {
		printBeans(annotationConfigApplicationContext);

		//工厂Bean获取的是调用getObject方法获取的对象
		Object colorFactoryBean = annotationConfigApplicationContext.getBean("colorFactoryBean");
		Object colorFactoryBean2 = annotationConfigApplicationContext.getBean("colorFactoryBean");
		Object colorFactoryBean3 = annotationConfigApplicationContext.getBean("colorFactoryBean");
		System.out.println("bean的类型:"+colorFactoryBean.getClass());
		System.out.println(colorFactoryBean2 == colorFactoryBean3);
		//如果就像获取factoryBean本身 就在bean名前加个&
		Object colorFactoryBean4 = annotationConfigApplicationContext.getBean("&colorFactoryBean");
		System.out.println(colorFactoryBean4.getClass());
	}

	private void printBeans(AnnotationConfigApplicationContext anno) {
		String[] beanDefinitionNames = anno.getBeanDefinitionNames();
		for (String s :
				beanDefinitionNames) {
			System.out.println(s);
		}
	}
}
