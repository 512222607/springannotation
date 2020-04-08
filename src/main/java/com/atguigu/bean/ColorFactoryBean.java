package com.atguigu.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author zhangzm
 * @date 2020/2/13 23:31
 */
//创建一个spring定义的FactoryBean
public class ColorFactoryBean implements FactoryBean<Color> {

	//返回一个color对象，这个对象会添加到容器中
	@Override
	public Color getObject() throws Exception {
		System.out.println("ColorFactoryBean...getObject...");
		return new Color();
	}

	//返回对象的类型
	@Override
	public Class<?> getObjectType() {
		return Color.class;
	}

	//是否单例 true表示单例，false表示每次调用创建新的
	@Override
	public boolean isSingleton() {
		return false;
	}
}
