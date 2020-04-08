package com.atguigu.config;

import com.atguigu.bean.Car;
import com.atguigu.bean.Cat;
import com.atguigu.bean.Color;
import com.atguigu.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author zhangzm
 * @date 2020/2/19 22:45
 */

/**
 * 自动装配：
 * 	spring利用依赖注入（DI），完成对IOC容器中的各个组件的依赖关系的赋值
 * 	1、@Autowired,自动注入。
 * 		a、默认优先按照类型去容器中找对应的组件，如果找到就赋值，相当于调用applicationContext.getBean(BookDao.class);
 * 		b、如果找到多个想同类型的组件，再将属性的名称作为组件的id去容器中查找。相当于调用applicationContext.getBean("bookDao2");
 * 		c、@Qualifier("bookDao")，使用该注解指定需要装配的组件的id，而不是使用属性名。相当于使用applicationContext.getBean("bookDao")赋值给属性bookDao2
 * 		d、自动装配魔人一定要将属性赋值好，否则启动就会报错。可以使用Autowired注解中的require属性设置为false。
 * 		e、@Primary，让spring进行自动装配的时候默认使用首选的bean,也可以继续使用@Qualifier注解指定需要装配的bean的名字
 *
 * 2、spring还支持使用@Resource（JSR250规范定义）和@Inject（JSR330规范定义）【Java规范的注解】
 * @Resource:可以和autowired一样实现自动装配功能，但是该注解默认按照组件名称装配，可以通过注解的name属性指定装配的组件id
 * @Inject:需要先导入javax.inject的包，以后和autowired的功能一致，唯一不同就是缺少required=false的功能。
 * <b1>@Autowired是spring定义的，@Resource、@Inject都是java规范，后两者脱离spring框架，因此使用其他IOC框架，也一样会支持。</b1>
 * 3、@Autowired：在构造器，参数，方法，属性均可标注:
 * 		a、【标注在方法位置】，@Bean+方法参数，参数从容器中获取 如下面的color；默认可以不写autowired
 * 		b、【标注在构造器上】，如果组件只有一个有参构造器，这个有参构造器的Autowired可以省略，参数位置的组件还是可以从容器中自动获取。
 * 		c、放在参数上
 * 	以上几种都是从容器中获取参数组件的值。
 *
 * 4、自定义组件想要使用Spring容器底层的一些组件如（ApplicationContext，BeanFactory，XXX）：
 * 		自定义组件只需要实现XXXAware接口即可，在创建对象的时候，会调用接口规定的方法注入相关组件：可以通过Aware接口把Spring底层一些组件注入到自定义的Bean中，样例如Red类
 * 		xxxAware的功能是使用XXXProcessor来处理的：
 * 			ApplicationContextAware ==》 ApplicationContextAwareProcessor
 * <p></p>
 */
@Configuration
@ComponentScan({"com.atguigu.controller","com.atguigu.service","com.atguigu.dao","com.atguigu.bean"})
public class MainConfigOfAutowired {


	@Bean("bookDao2")
	public BookDao bookDao() {
		BookDao bookDao = new BookDao();
		bookDao.setLable("2");
		return bookDao;
	}

	@Primary
	@Bean("bookDao3")
	public BookDao bookDao3() {
		BookDao bookDao = new BookDao();
		bookDao.setLable("3");
		return bookDao;
	}

	@Bean
	/**
	 * @bean标注的方法创建对象的时候，方法的参数值可以从容器中获取。
	 */
	public Color color(Car car) {
		Color color = new Color();
		color.setCar(car);
		return color;
	}
}
