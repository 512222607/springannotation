package com.atguigu.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhangzm
 * @date 2020/2/13 23:12
 */

//自定义逻辑返回需要导入的组件
public class MyImportSelector implements ImportSelector {

	//返回值就是要导入到容器中的组件的全类名
	//AnnotationMetadata:能获取@Import注解所在的类的所有注解信息
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//		importingClassMetadata.getAnnotatedMethods()
		return new String[]{"com.atguigu.bean.Blue","com.atguigu.bean.Yellow"};
	}
}
