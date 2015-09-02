package com.bxb.common.util;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings("rawtypes")
public class BeanUtil {
	/**
	 * 将map中的对象设置到entityClass类型的对象上，如果entityClass不含该属性，则不设置
	 * 
	 * @param entityClass
	 * @param request
	 * @return
	 */
	public static <T> T copyProperties(Class<T> entityClass, Map map) {
		try {
			T entity = entityClass.newInstance();

			Field[] fields = entityClass.getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				String name = f.getName();
				// String tp = f.getGenericType().toString(); //获取属性的类型

				if (map.containsKey(name)) {
					BeanUtils.copyProperty(entity, name, map.get(name));
				}
			}

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
