package org.springframework.demo.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;

public class ContextUtils {
	public static void showInfo(ApplicationContext appContext) {
		System.out.println("------------------");
		System.out.println("id: " +appContext.getId());
		System.out.println("name: " +appContext.getApplicationName());
		System.out.println("display name: " +appContext.getDisplayName());
		System.out.println("startup date: " +new Date(appContext.getStartupDate()));
		System.out.println("bean definition name: " +StringUtils.join(appContext.getBeanDefinitionNames(),","));
		ApplicationContext parent = appContext.getParent();
		System.out.println("parent context: "+ ( parent!=null ? parent.getDisplayName():"none"  ));
		System.out.println("------------------");
	}
	public static <T>T getSprinBean(ApplicationContext appContext,Class<T> clazz){
		AutowireCapableBeanFactory factory = appContext
				.getAutowireCapableBeanFactory();

		if (factory instanceof DefaultListableBeanFactory) {
			DefaultListableBeanFactory dlbFactory = (DefaultListableBeanFactory) factory;
			/**
			 * DefaultListableBeanFactory 不僅提供相關資訊,而且還可以修改spring bean
			 * **/
			T bean = dlbFactory.getBean(clazz);
			
			return bean;
		}else{
			/**
			 * ApplicationContext 僅提供相關資訊,但是無法進行修改
			 * **/
			return appContext.getBean(clazz);
		}
	}
}
