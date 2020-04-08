package com.atguigu.config;

import com.atguigu.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author zhangzm
 * @date 2020/2/14 22:19
 */

/**
 * bean 的生命周期
 * bean创建 --》 初始化 --》 销毁的过程  就叫做bean的生命周期
 * 现在是由容器管理bean的生命周期
 * 我们可以自定义初始化和销毁的方法，容器在bean进行到当前生命周期的时候会调用我门自定义的初始化和销毁的方法
 * <p>容器启动后，bean的生命周期如下<br>
 *(1)构造方法（对象创建）
 *		单实例的时候在容器启动的时候创建对象
 * 		多实例在每次获取的时候创建对象<br>
 *(2)BeanPostProcessor#postProcessBeforeInitialization()方法调用<br>
 *(3)初始化：
 * 		对象创建完成，并赋值好，调用初始化方法<br>
 *(4)BeanPostProcessor#postProcessAfterInitialization()方法调用<br>
 *(5)销毁：
 * 		单实例，在容器关闭的时候调用销毁方法
 * 		多实例，容器不会管理这个bean，所以容器不会调用销毁方法。<br><br>
 * <p><b>源码执行流程：给bean进行属性赋值->
 *     遍历容器中所有的BeanPostProcessor，挨个调用postProcessBeforeInitialization方法，一旦其中有一个返回null，则跳出for循环，不会执行后续的beanPostProcessor->执行自定义bean初始化方法->遍历容器所有遍历容器中所有的BeanPostProcessor，挨个调用postProcessAfterInitialization方法，一旦其中有一个返回null，则跳出for循环。<br>
 *     部分源码：/org/springframework/beans/factory/support/AbstractAutowireCapableBeanFactory.java:576</b><br>
 * populateBean(beanName, mbd, instanceWrapper);给bean进行属性赋值，调用getset方法等,然后调用后续初始化方:<br>
 * {
 * applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);调用beanPostProcessor方法
 * invokeInitMethods(beanName, wrappedBean, mbd);执行自定义bean初始化方法
 * applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);调用beanPostProcessor方法
 * }
 * </p>
 *
 *
 *<br>
 * <p><b>初始化的实现方式有以下几种</b><br>
 * 1、指定初始化和销毁方法 对应以前xml中的init-method 和destroy-method属性
 *
 * 2、spring提供了两个接口，InitializingBean，DisposableBean
 * 通过让bean实现InitializingBean(定义初始化逻辑)
 * 通过让bean实现DisposableBean(定义销毁逻辑)
 *
 * 3、可以使用JSR250规范中定义的注解：
 * 		@PostConstruct，在bean创建完成并且属性设置完成后，来执行初始化方法，该注解写在方法上。
 * 		@PreDestroy，在容器销毁bean之前，通知我们进行清理工作。
 *
 * 	4、BeanPostProcessor:bean的后置处理器
 * 		在bean初始化前后进行一些处理工作:
 * 			postProcessBeforeInitialization:在所有的初始化方法之前进行调用（init-method/afterPropertiesSet等方法之前）
 * 			postProcessAfterInitialization:在所有的初始化方法之后进行调用（init-method/afterPropertiesSet等方法之后）
 * 	</p>
 *
 * 	<p><b>Spring底层对BeanPostProcessor的使用</b><br>
 * 		bean的赋值，注入其他组件，@Autowired，生命周期注解，@Async，xxx BeanPostProcessor等等的实现都是通过BeanPostProcessor接口来实现的。
 * 	</p>
 */
@ComponentScan("com.atguigu.bean")
@Configuration
public class MainConfigOfLifeCycle {

//	@Scope("prototype")
	@Bean(initMethod = "init",destroyMethod = "destroy")
	public Car car() {
		return new Car();
	}

}
