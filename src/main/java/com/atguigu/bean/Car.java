package com.atguigu.bean;

import org.springframework.stereotype.Component;

/**
 * @author zhangzm
 * @date 2020/2/14 22:22
 */
@Component
public class Car {

	public Car() {
		System.out.println("car constructor");
	}

	public void init() {
		System.out.println("car ... init");
	}

	public void destroy() {
		System.out.println("car ... destroy");
	}
}
