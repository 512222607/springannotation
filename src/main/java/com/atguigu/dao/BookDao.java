package com.atguigu.dao;

import com.atguigu.annotation.ExcludeAnno;
import org.springframework.stereotype.Repository;

/**
 * @author zhangzm
 * @date 2019/7/8 15:59
 */
@ExcludeAnno
@Repository
public class BookDao {

	private String lable = "1";

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	@Override
	public String toString() {
		return "BookDao{" +
				"lable='" + lable + '\'' +
				'}';
	}
}
