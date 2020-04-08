package com.atguigu.bean;

/**
 * @author zhangzm
 * @date 2019/7/8 17:38
 */
public class Color {

	private String color;

	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Color{" +
				"color='" + color + '\'' +
				", car=" + car +
				'}';
	}
}
