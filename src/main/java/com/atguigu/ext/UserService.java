package com.atguigu.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author zhangzm
 * @date 2020/3/26 22:57
 */
@Service
public class UserService {

	//需要拿到的事件就放在参数的位置
	@EventListener(classes = {ApplicationEvent.class})
	public void listen(ApplicationEvent event) {
		System.out.println("UserService...监听到的事件："+event);
	}
}
