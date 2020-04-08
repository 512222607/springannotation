package com.atguigu.config;

/**
 * @author zhangzm
 * @date 2020/2/24 22:36
 */

import com.atguigu.aop.LogAspect;
import com.atguigu.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP:[动态代理]
 * 		指在程序运行期间动态的将某段代码切入到指定方法的指定位置进行运行的编程方式。
 * 	1、导入aop模块：Spring AOP(spring-aspects依赖)
 * 	2、定义一个业务逻辑类（MathCaculator）：希望在业务逻辑运行的时候将日志进行打印（方法之前，方法之后，方法参数，方法抛出的异常信息等）
 *	3、定义一个日志切面类（LogAspect），切面类里面的方法需要动态感知MathCalculator.div方法运行到哪里然后执行。
 *			通知方法：
 *				前置通知（@Before）；logStart 在目标方法（div）运行之前运行
 *				后置通知（@After）；logEnd 在目标方法（div）运行结束之后运行,无论方法正常结束还是异常结束
 *				返回通知（@AfterReturning）；logReturn 在目标方法（div）正常返回之后运行
 *				异常通知（@AfterThrowing）；logException 在目标方法（div）出现异常之后运行
 *				环绕通知（@Around）；动态代理，手动推进目标方法运行（joinPoint.procced()）。
 *	4、给切面类的目标方法标注通知注解，指定何时何地运行
 * 	5、将切面类和业务逻辑类（目标方法所在类）都加入到容器中
 * 	6、必须告诉容器哪个类是切面类，在切面上增加注解（@Aspect）
 * 	7、在配置类中加上注解@EnableAspectJAutoProxy注解启用aspectj自动代理（开启基于注解的aop模式）
 *
 * 	spring中最多的就是@EnableXXX，都是用来开启spring容器的某些功能
 *
 * 	AOP实现的三步：
 * 		1、将业务逻辑组件和切面类都加入到容器，告诉spring容器那个是切面类（@Aspect）
 * 		2、在切面类上的每一个通知方法上标注通知注解，告诉spring何时何地运行（切入点表达式）
 * 		3、开启基于注解的aop模式（@EnableAspectJAutoProxy）
 *
 * 	AOP的原理：（spring的原理基本都是-看给容器中注册了什么组件，这个组价什么时候工作，这个组件工作时候的功能是什么）
 * 	@EnableAspectJAutoProxy注解
 * 		1、@EnableAspectJAutoProxy注解是什么：
 * 			@Import(AspectJAutoProxyRegistrar.class)，给容器中导入AspectJAutoProxyRegistrar利用它自定义给容器中注入Bean。
 * 				internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 * 			给容器中注册一个叫AnnotationAwareAspectJAutoProxyCreator组件：
 * 		2、AnnotationAwareAspectJAutoProxyCreator组件原理：
 * 			AnnotationAwareAspectJAutoProxyCreator(注解声明式aspectj自动代理创建器)
 * 				->AspectJAwareAdvisorAutoProxyCreator
 * 					->AbstractAdvisorAutoProxyCreator
 * 						->AbstractAutoProxyCreator
 * 							implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 * 							关注后置处理器（在bean初始化完成前后所做的事情）、自动装配BeanFactory做了什么事。
 * 			一些主要的方法在：
 * 			AbstractAutoProxyCreator.setBeanFactory()
 * 			AbstractAutoProxyCreator有后置处理器相关的逻辑
 * 			AbstractAdvisorAutoProxyCreator.setBeanFactory()调用了initBeanFactory()
 *			AnnotationAwareAspectJAutoProxyCreator.initBeanFacory()
 *
 *
 * 调用流程：
 * 1、传入配置类，创建ioc容器。
 * 2、注册配置类，调用refresh()方法刷新容器（创建容器中的所有bean即初始化容器）
 * 3、registerBeanPostProcessors(beanFactory)；注册bean的后置处理器来方便拦截bean的创建：
 * 		1）、先获取ioc容器中<b>已经定义了</b>的需要创建对象的所有BeanPostProcessor（如EnableAspectJAutoProxy、AutoWired等注解的定义，这些注解都是通过beanPostProcessor实现的）
 * 		2）、给容器中加别的beanPostProcessor
 * 		3）、优先注册实现了PriorityOrdered接口的beanPostProcessor
 * 		4）、再给容器中注册实现了Ordered接口的beanPostProcessor
 * 		5）、再将普通的 未实现优先级接口的beanPostProcessor的接口注册到容器中
 * 		6）、注册beanPostProcessor，实际上就是创建beanPostProcessor对象，保存在容器中：
 * 			创建internalAutoProxyCreator的BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]（创建bean的流程）：
 * 				1、创建Bean的实例
 * 				2、populateBean，给Bean的给中属性赋值
 * 				3、InitializeBean，初始化Bean：
 * 					a、invokeAwareMethods()：处理aware接口的方法回调（此时调用了AbstractAutoProxyCreator.setBeanFactory()方法）
 * 					b、applyBeanPostProcessorsBeforeInitialization()：应用后置处理器的postProcessorsBeforeInitialization()方法
 * 					c、invokeInitMethods执行自定义的初始化方法
 * 					d、applyBeanPostProcessorsAfterInitialization():执行后置处理器的PostProcessorsAfterInitialization()方法
 * 				4、BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功，并且调用了initBeanFactory方法。
 * 		7）、把BeanPostProcessor注册到BeanFactory中：
 * 			beanFactory.addBeanPostProcessor(postProcessor);
 * ==================以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程=================
 *在初始化之后的对象再进行创建和注册的过程中，就会用到上面创建的这个后置处理器了。
 * <p>
 *     AnnotationAwareAspectJAutoProxyCreator并不是最简单的BeanPostProcessor，而是InstantiationAwareBeanPostProcessor，与基础的BeanPostProcessor.postProcessBeforeInitialization方法不同.
 *     【BeanPostProcessor】是在bean对象创建完成的初始化前后调用
 *     【InstantiationAwareBeanPostProcessor】是在bean实例化之前先尝试使用后置处理器返回对象的实例，如AnnotationAwareAspectJAutoProxyCreator
 * </p>
 * 4、finishBeanFactoryInitialization(beanFactory)；完成BeanFactory初始化工作，创建剩下的单实例bean
 * 		1）、先是遍历获取容器中所有的bean，然后依次创建对象getBean(beanName)
 * 			调用关系getBean->doGetBean()->getSingleton():
 * 		2)、创建bean 实际上走的是doGetBean方法：
 * 			【AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前有一个拦截，因为他是InstantiationAwareBeanPostProcessor类型的后置处理器，会调用postProcessBeforeInstantiation】
 * 			a、先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用，否则再创建。
 * 				只要创建好的bean都会被缓存起来
 * 			b、createBean(),创建bean
 * 				1、resolveBeforeInstantiation(beanName, mbdToUse);解析BefortInstantiation。
 * 					希望后置处理器在此能返回一个代理对象，如果能返回代理对象则直接返回代理对象，如果不能就继续走后续的2，如果返回代理对象步骤如下：
 * 						（1）、后置处理器先尝试返回对象
 * 							bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);  拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor,就执行postProcessBeforeInstantiation
 * 							if (bean != null) {
 * 								bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 * 							}
 *
 * 				2、调用doCreateBean()，真正的去创建一个bean实例的流程，和上面的3.6的流程完全一样。
 *
 *
 *AnnotationAwareAspectJAutoProxyCreator是InstantiationAwareBeanPostProcessor类型的后置处理器，它的作用：
 * 1、每一个bean创建之前，调用postProcessBeforeInstantiation():
 * 		我们先只关心下面的MathCalculator和LogAspect的创建
 * 		1）、判断当前的bean是否在advisedBeans中（保存了所有需要增强的bean）
 * 		2）、判断当前bean是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean，或者是否是切面（标有@Aspect注解）
 * 		3）、是否需要跳过：
 * 			a、获取候选的增强器（切面里面的通知方法）【List<Advisor> candidateAdvisors】
 * 				每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor，
 * 				判断每一个增强器是否是AspectJpointcutAdvisor类型的，是则返回true
 * 			b、永远返回false
 *
 * 2、创建对象：
 * postProcessAfterInitialization：
 * 		return wrapIfNecessary(bean, beanName, cacheKey); //包装如果需要的情况下
 * 		1）、获取当前bean的所有增强器（通知方法）Object[] specificInterceptors
 * 			a、找到的所有增强器
 * 			b、获取到能在当前bean使用的增强器，（找哪些通知方式是需要切入当前bean方法的）
 * 			c、给增强器排序
 *		2）、保存当前bean在advisedBeans中，表示已被增强处理
 *		3）、如果当前bean需要增强，创建bean的代理对象 调用方法createProxy()：
 *			a、获取所有增强器（通知方法）
 *			b、保存到proxyFactory中
 *			c、创建代理对象，spring自动决定
 *				JdkDynamicAopProxy：jdk动态代理
 *				ObjenesisCglibAopProxy：cglib的动态代理
 *		4）、给容器中返回当前组件使用cglib增强的代理对象
 *		5）、以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程。
 *
 * 3、目标对象执行：
 * 		容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如所有的增强器，目标对象等等）
 * 		1）、CglibAopProxy.intercept();拦截目标方法的执行
 * 		2）、根据ProxyFactory对象获取将要执行的目标方法的拦截器链，
 *			List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *		3）、如果没有拦截器链，则直接执行目标方法。
 *		4）、如果有拦截器链，把需要执行的目标对象，目标方法
 *			拦截器链等信息传入创建一个CglibMethodInvocation对象并调用Object retval = mi.proceed()方法，mi是methodInvocation对象。
 *		5)、拦截器链的触发过程；
 *			a、如果没有拦截器则执行目标方法，或者拦截器的index和拦截器数组大小-1一样（指定到了最有一个拦截器）则执行目标方法。
 *			b、链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成后返回以后再来执行。
 *				拦截器链的机制，保证通知方法与目标方法的执行顺序，在2）中获取拦截器链的时候进行的排序就是从后向前排序，expost -》 after throw -》after return-》 after -》 before
 *				expost主要是讲mi放入threadLocal中	便于后续的interception中获取当前的cglibMethodInvocation（mi）调用getJoinPointMatch()方法,判断是否需要执行某个通知。当抛出异常的时候因after的逻辑是写在finally中的所以一定会走，但是afterReturn是直接调用后续的通知方法所以会跳过，进入afterThrow的catch中。
 *
 *总结：
 * 	1）、@EnableAspectJAutoProxy开启aop功能
 * 	2）、@EnableAspectJAutoProxy会给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator
 * 	3）、AnnotationAwareAspectJAutoProxyCreator是一个后置处理器，
 * 	4）、容器的创建流程：
 * 			a、registerBeanPostProcessor()注册后置处理器，创建AnnotationAwareAspectJAutoProxyCreator对象
 * 			b、finishBeanFactoryInitialization()初始化剩下的单实例bean
 * 				1、创建业务逻辑组件和切面组件
 * 				2、	AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 * 				3、组件创建完成之后判断组件是否需要增强，如果需要增强，则把切面中的通知方法包装成增强器（Advisor）。给业务逻辑组件创建一个代理对象。
 * 	5）、执行目标方法，
 * 			a、代理对象执行目标方法
 * 			b、CglibAopProxy.intercept()
 * 				1、得到目标方法的拦截器链（将之前的增强器包装成拦截器MethodInterceptor）
 * 				2、利用拦截器的链式机制，依次进入每一个拦截器进行之星
 * 				3、效果
 * 					正常执行：前置通知-》目标方法-》后置通知-》返回通知
 * 					目标方法出现异常：前置通知-》目标方法-》后置通知-》异常通知
 */
@Configuration
@EnableAspectJAutoProxy
public class MainConfigOfAOP {

	@Bean
	public MathCalculator mathCalculator() {
		return new MathCalculator();
	}

	@Bean
	public LogAspect logAspect() {
		return new LogAspect();
	}
}
