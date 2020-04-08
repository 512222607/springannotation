package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangzm
 * @date 2020/2/20 22:11
 */

//默认加载IOC容器中的组件，容器启动会调用无参构造器创建对象，再进行初始化赋值等操作
@Component
public class Boss {

	private Car car;

//	@Autowired
	//构造器要用的组件，也都是从容器中获取
	public Boss(@Autowired Car car) {
		this.car = car;
		System.out.println("spring 调用 boss 有参构造器");
	}

	@Override
	public String toString() {
		return "Boss{" +
				"car=" + car +
				'}';
	}

	public Car getCar() {
		return car;
	}

//	@Autowired
	//标注在方法上，spring容器创建当前对象的时候会调用该方法完成赋值：
	//方法使用的参数，自定义类型的值从IOC容器中获取
	public void setCar(Car car) {
		this.car = car;
	}
}
