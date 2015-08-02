package com.bxb.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring工具栏
 * 
 */
public class WebContextUtil implements ApplicationContextAware {

	private static ApplicationContext appCtx;

	/**
	 * 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。
	 * 
	 * @param applicationContext
	 *            ApplicationContext 对象.
	 * @throws BeansException
	 * @author
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appCtx = applicationContext;
	}

	/**
	 * 获取ApplicationContext
	 * 
	 * @return
	 * @author
	 */
	public static ApplicationContext getApplicationContext() {
		return appCtx;
	}

	/**
	 * 获取一个BEAN
	 * 
	 * @param beanName
	 *            bean的名字
	 * @return 返回一个bean对象
	 * @author
	 */
	public static Object getBean(String beanName) {
		return appCtx.getBean(beanName);
	}
}
