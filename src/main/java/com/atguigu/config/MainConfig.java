package com.atguigu.config;

/**
 * @author zhangzm
 * @date 2019/7/8 15:46
 */

import com.atguigu.annotation.ExcludeAnno;
import com.atguigu.bean.Person;
import com.atguigu.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * 配置类
 */
@Configuration  //告诉spring这是一个配置类
@ComponentScans(
		@ComponentScan(value = {"com.atguigu"}, excludeFilters = {
//				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {ExcludeAnno.class}),
//				@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
//				@ComponentScan.Filter(type = FilterType.REGEX, pattern = ""), //正则表达式
				@ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class}), //自定义规则
		},useDefaultFilters = false)
)
//可以配置扫描的规则 excludeFilters扫描的时候按照指定的规则排除
//includeFilters 扫描的时候只包含什么
//FilterType.ANNOTATION 指定过滤器规则是注解方式
//FilterType.ASSIGNABLE_TYPE 直接按照给定类型
//FilterType.ASPECTJ 使用aspectj表达式的
public class MainConfig {

	@Bean(value = "person") //给容器中注册一个bean id默认用方法名做id
	public Person person01() {
		return new Person("张三", 20);
	}

}
