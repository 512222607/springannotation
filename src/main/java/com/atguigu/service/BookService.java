package com.atguigu.service;

import com.atguigu.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author zhangzm
 * @date 2019/7/8 15:58
 */

@Service
public class BookService {

//	@Qualifier("bookDao")
	@Autowired(required = false)
	private BookDao bookDao2;

	public void print() {
		System.out.println(bookDao2);
	}

	@Override
	public String toString() {
		return "BookService{" +
				"bookDao=" + bookDao2 +
				'}';
	}
}
