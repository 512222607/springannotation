package com.atguigu.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangzm
 * @date 2020/3/15 22:35
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public void  inserUser() {
		userDao.insert();
		System.out.println("插入完成");

		int i = 10 / 0;
	}
}
