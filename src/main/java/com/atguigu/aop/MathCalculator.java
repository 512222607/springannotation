package com.atguigu.aop;

/**
 * @author zhangzm
 * @date 2020/2/24 22:39
 */
public class MathCalculator {

	public int div(int i, int j) {
		System.out.println("MathCalculator...div...调用");
		return i / j;
	}

}
