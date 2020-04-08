package com.atguigu.tx;

/**
 * @author zhangzm
 * @date 2020/3/15 22:20
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 声明式事务
 * <p>
 * 环境搭建：
 * 1、导入相关依赖
 * 数据源，数据库驱动，spring-jdbc模块
 * 2、配置数据源，JdbcTemplate（spring提供的简化数据库操作的工具）操作数据
 *
 *
 *原理：
 * 1、@EnableTransactionManagement
 * 	 利用import加载TransactionManagementConfigurationSelector给容器中导入组件
 * 	 导入两个组件：
 * 	 	AutoProxyRegistrar：
 * 	 		给容器中导入一个InfrastructureAdvisorAutoProxyCreator组件：
 * 	 			InfrastructureAdvisorAutoProxyCreator只是利用后置处理器机制，在对象创建以后包装对象，返回一个代理对象（有增强器），代理对象执行方法利用拦截器链进行调用。
 *		ProxyTransactionManagementConfiguration：
 *			a、给容器中注入transactionAdvisor（事务增强器）这个增强器有两个属性：
 *				1）事务增强器要用的事务注解的信息，AnnotationTransactionAttributeSource解析事务注解
 *				2）事务拦截器：
 *					TransactionInterceptor：保存了事务属性信息，以及事务管理器
 *					它是一个methodInterceptor
 *					在目标方法执行的时候执行这些拦截器链，而这个拦截器链中只有一个TransactionInterceptor，这个拦截器在代理类的inceptor方法中调用proceed里面的invoke方法时执行的功能如下：
 *						（1）getTransactionAttributeSource():先获取事务相关的属性
 *						（2）determineTransactionManager():再获取platformTransactionManager，如果事先没有添加指定任何transactionmanger，最终会从容器中按照类型获取一个PlatformTransactionManager
 *						（3）执行目标方法：
 *								如果异常，获取到事务管理器，利用事务管理器回滚操作completeTransactionAfterThrowing()
 *							 	如果正常，利用事务管理器提交事务commitTransactionAfterReturning()
 *
 */

@EnableTransactionManagement
@ComponentScan("com.atguigu.tx")
@Configuration
public class TxConfig {

	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		//spring对@Configuration类会特殊处理，给容器中增加组件的方法，多次调用@bean标注的方法都只是从容器中找到组件，如new JdbcTemplate(dataSource())这种写法
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
}
