package com.atguigu.config;

import com.atguigu.bean.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * profile：
 * 		spring为我们提供的，可以根据当前环境，动态的激活和切换一系列组件的功能。
 * 	开发环境，测试环境，生产环境：
 * 	以数据源为例（开发环境连A库）（测试连B库）（生产连C库）
 *
 * 	1、加了环境标识的bean，只有这个环境被激活的时候才会注入到容器中，默认是default环境。
 * 	2、该注解还可以写在类上，只有是指定的环境的时候，整个配置类里面的所有配置才能生效。
 * 	3、没有标注环境表示的bean在任何环境下都是加载的。
 *
 * 	<p>切换环境的方法：
 * 	1 运行的时候执行参数-Dspring.profiles.active=xxx指定环境。
 * 	2 代码的方式：xxxApplicationContext创建的时候使用无参构造器，然后里面有getEnvironment方法可以获取环境，然后setActiveProfiles方法设置需要激活的环境，能够一次指定多个，然后调用register可以注册配置类，最后调用applicationContext的refresh启动刷新容器。
 * 	</p>
 * @author zhangzm
 * @date 2020/2/22 22:51
 */

//@Profile("test")
@Configuration
public class MainConfigOfProfile {

	@Profile("default")
	@Bean
	public Color colorDefault() {
		Color color = new Color();
		color.setColor("default");
		return color;
	}

	@Profile("test")
	@Bean
	public Color colorTest() {
		Color color = new Color();
		color.setColor("test");
		return color;
	}

	@Profile("dev")
	@Bean
	public Color colorDev() {
		Color color = new Color();
		color.setColor("dev");
		return color;
	}

}
