package com.atguigu.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author zhangzm
 * @date 2020/2/14 22:53
 */
@Component
public class Dog {

	public Dog() {
		System.out.println("dog ... constructor");
	}

	//对象创建并赋值之后调用 （post表示什么什么之后）
	@PostConstruct
	public void postConstruct() {
		System.out.println("dog ... @postConstruct");
	}

	@PreDestroy
	public void destory() {
		System.out.println("dog ... @PreDestroy");
	}
}
