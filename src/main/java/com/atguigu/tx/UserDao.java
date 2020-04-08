package com.atguigu.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author zhangzm
 * @date 2020/3/15 22:35
 */
@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert() {
		String sql = "insert into 'tbl_user'(username,age) values(?,?)";
		String substring = UUID.randomUUID().toString().substring(0, 5);
		jdbcTemplate.update(sql, substring,19);
	}
}
