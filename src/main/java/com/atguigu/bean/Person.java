package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author zhangzm
 * @date 2019/7/8 15:42
 */
public class Person {

	@Value("张三")
	private String name;

	@Value("#{20-2}")
	private int age;

	@Value("${person.nickName}")
	private String nickName;

	public Person() {
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Person(String name, int age,String nickName) {
		this.name = name;
		this.age = age;
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				", nickName='" + nickName + '\'' +
				'}';
	}
}
