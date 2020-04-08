package com.atguigu.config;

import com.atguigu.bean.Color;
import com.atguigu.bean.ColorFactoryBean;
import com.atguigu.bean.Person;
import com.atguigu.condition.LinuxCondition;
import com.atguigu.condition.MyImportBeanDefinitionRegister;
import com.atguigu.condition.MyImportSelector;
import com.atguigu.condition.WindowsCondition;
import com.sun.deploy.net.protocol.ProtocolType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author zhangzm
 * @date 2019/7/8 16:41
 */
@Component
@Import({Color.class, MyImportSelector.class, MyImportBeanDefinitionRegister.class}) //给容器中快速导入一个组件  id默认是组件的全类名。
public class MainConfig2 {

	@Bean(value = "person") //给容器中注册一个bean id默认用方法名做id
//	@Scope("prototype")  //指定作用范围
	@Lazy
	public Person person() {
		System.out.println("给容器中添加person");
		return new Person("张三", 25);
	}


	/**
	 * @return
	 */
	@Conditional({WindowsCondition.class})
	@Bean("bill")
	@Lazy
	public Person person01() {
		return new Person("bill gates", 62);
	}

	@Conditional({LinuxCondition.class})
	@Bean("linux")
	public Person person02() {
		return new Person("linux", 55);
	}

	/**
	 * 给容器中注册组件的方式
	 * 1、 包扫描+组件标注注解（@controller、@service、@repository、@component）【局限于自己能够编写的类】
	 * 2、 @Bean【导入的第三方包里面的组件】
	 * 3、 @Import【快速的给容器中注册一个对象】
	 * 		1、@Import(要导入到容器中的组件),容器会自动注册这个，id默认是全类名
	 * 		2、ImportSelector:返回需要导入的组件的全类名数组
	 * 		3、ImportBeanDefinitionRegistrar:手动注册bean到容器中，自己指定名字。
	 * 4、使用Spring提供的FactoryBean（工厂Bean）
	 * 		1、默认获取到的是工厂bean调用getObject方法后创建的对象
	 * 		2、要获取工厂bean本身，需要在id前加一个&，因为在beanFactory中定义了FactoryBean的前缀是&
	 */

	@Bean
	public ColorFactoryBean colorFactoryBean() {
		return new ColorFactoryBean();
	}
}
