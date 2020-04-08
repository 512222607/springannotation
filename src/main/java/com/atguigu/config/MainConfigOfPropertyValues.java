package com.atguigu.config;

import com.atguigu.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zhangzm
 * @date 2020/2/18 23:12
 */
//使用@PropertySource读取外部配置文件中的K/V保存到运行的环境变量中;家在外部配置文件以后使用${}的方式获取
@PropertySource(value = {"classpath:/person.properties"})
@Configuration
public class MainConfigOfPropertyValues {

	@Bean
	public Person person() {
		return new Person();
	}
}
