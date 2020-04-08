package com.atguigu.ext;

import com.atguigu.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangzm
 * @date 2020/3/18 22:36
 */

/**
 * 扩展原理：
 *
 * 	BeanPostProcessor：bean的后置处理器，是在bean创建对象，初始化前后进行拦截工作的
 *
 * 	1、BeanFactoryPostProcessor：beanFactory的后置处理器：
 * 		在BeanFactory标准初始化之后调用（是指所有的bean定义已经保存加载到beanFactory中，但是bean的实例还未创建）
 *
 * 		1)、ioc容器创建对象
 * 		2)、invokeBeanFactoryPostProcessors(beanFactory);执行beanFactoryPostProcessor;
 * 			如何找到所有的BeanFactoryPostProcessors并执行他们的方法，
 * 				1）、直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法。
 * 				2）、在初始化创建其他组件前面执行。
 *
 * 	2、BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 * 		postProcessBeanDefinitionRegistry();
 * 		在所有bean定义信息将要被加载，bean实例还未创建的之前执行。
 *
 * 		优先于BeanFactoryPostProcessor执行，
 * 		利用BeandifinitionRegistryPostProcessor给容器中再额外添加一些组件；
 *
 * 	原理：
 * 		1）、ioc容器准备创建，
 * 		2）、refresh() -》 invokeBeanFactoryPostProcessors(beanFactory);
 * 		3）、从容器中中获取到所有的BeanDefinitionRegistry组件
 * 			a、依次出发所有的postProcessBeanDefinitionRegistry()方法。
 * 			b、再来依次触发postProcessBeanFactory()方法
 * 		4）、再从容器中找到BeanFactoryPostProcessor组件，然后依次调用postProcessBeanFactory()方法
 *
 *
 *
 * 	3、ApplicationListener，监听容器中发布的事件，事件驱动的开发：
 * 		public interface ApplicationListener<E extends ApplicationEvent> extends EventListener
 * 			监听ApplicationEvent及其下面的子事件
 *
 * 		基于事件开发的步骤：
 * 			1、写一个监听器(ApplicationListener的实现类)来监听某个事件（这个事件属于ApplicationEvent及其子类）
 * 				或者使用@EventListener注解，可以让任意方法都能监听事件
 *			2、监听器加入到容器。
 *			3、只要容器中有相关事件的发布，我们就能监听到这个事件。
 *				ContextRefreshedEvent，容器刷新完成（所有的bean都完全创建）会发布这个事件。
 *				ContextClosedEvent，关闭容器会发布这个事件
 *			4、发布一个事件：
 *					调用容器的publishEvent()方法，如applicationContext.publishEvent();
 *
 *		事件的原理：分别来看ContextRefreshedEvent、IOCTest_Ext$1[source=我发布了一个事件]、ContextClosedEvent三个事件。
 *		1)、ContextRefreshedEvent事件：
 *			a、容器创建对象，调用refresh();
 *			b、finishRefresh();容器刷新完成,然后会发布ContextRefreshedEvent事件
 *			c、调用publishEvent()发布事件
 *		2)、自己发布事件，自己调用publishEvent()
 *		3)、容器关闭会发布事件ContextClosedEvent；
 *
 *		publishEvent()事件发布流程：
 *			c、发布事件，调用publishEvent(new ContextRefreshedEvent(this));
 *					1、获取事件的多播器（派发器），getApplicationEventMulticaster()
 *					2、调用multicastEvent方法派发事件。
 *					3、获取到所有的ApplicationListener，
 *							for (final ApplicationListener<?> listener : getApplicationListeners(event, type))
 *								（1）、如果有Executor可以支持使用Executor进行异步派发。
 *										Executor executor = getTaskExecutor();
 *								（2）、否则，以同步的方式直接执行listener方法，invokeListener(listener,event);
 *										拿到listener，回调listener.onApplicationEvent(event);
 *
 *
 *	【事件多播器（派发器）】的获取过程：
 *		1、容器创建，调用refresh()
 *		2、在refresh中调用initApplicationEventMulticaster();初始化ApplicationEventMulticaster；
 *			a、先去容器中找是否有beanName为applicationEventMulticaster的组件，如果有从bean工厂中返回。
 *			b、如果没有就创建一个this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);并且注册到容器中，我们就可以在其他组件要派发事件的时候自动注入这个applicationEventMulticaster。
 *
 *	【容器中有哪些监听器（监听器的获取流程）getApplicationListeners()方法怎么获取】：
 *		1、容器创建，调用refresh()
 *		2、在refresh中调用registerListeners();
 *		3、String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 *
 *
 * @EventListener原理：
 * 		使用EventListenerMethodProcessor处理器来解析方法上面的EventListener注解。
 * 		它实现了一个接口叫SmartInitializingSingleton
 * 	SmartInitializingSingleton原理：调用-》afterSingletonsInstantiated();
 * 		1、ioc容器创建，并调用refresh刷新容器
 * 		2、finishBeanFactoryInitialization(beanFactory);初始化剩下的单实例bean。
 * 			1）、先创建所有的单实例bean，调用getBean()方法。
 * 			2）、获取所有创建好的单实例bean，判断是否是SmartInitializingSingleton类型的，如果是则调用afterSingletonsInstantiated();
 */
@ComponentScan("com.atguigu.ext")
@Configuration
public class ExtConfig {

	@Bean
	public Blue blue() {
		return new Blue();
	}
}
