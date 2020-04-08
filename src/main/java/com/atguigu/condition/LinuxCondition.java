package com.atguigu.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zhangzm
 * @date 2019/7/8 17:23
 */
public class LinuxCondition implements Condition{
	public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
		// 获取环境信息(包括环境变量，虚拟机信息等)
		Environment environment = conditionContext.getEnvironment();
		String property = environment.getProperty("os.name");
		if (property.contains("Linux")) {
			return true;
		}
		return false;
	}
}
