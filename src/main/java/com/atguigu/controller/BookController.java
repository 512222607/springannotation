package com.atguigu.controller;

import com.atguigu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author zhangzm
 * @date 2019/7/8 15:58
 */

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
}
