package com.atguigu.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author zhangzm
 * @date 2019/7/8 16:29
 */
public class MyTypeFilter implements TypeFilter {


	/**
	 *
	 * @param metadataReader 读取到当前正在扫描的类的信息
	 * @param metadataReaderFactory 可以获取到其他任何类信息的
	 * @return
	 * @throws IOException
	 */
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();//获取当前类注解的信息
		ClassMetadata classMetadata = metadataReader.getClassMetadata();//获取当前正在扫面的类的信息(类型，实现了什么接口等)
		Resource resource = metadataReader.getResource();//获取当前正在扫描的类的资源信息（路径等等）
		String className = classMetadata.getClassName();
		System.out.println("当前类的完整类名:"+className);
//		System.out.println(classMetadata.getSuperClassName());
//		System.out.println(classMetadata.isAnnotation());
		if (className.contains("er")) {
			return true;
		}
		return false;
	}
}
