package com.bxb.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author NBQ
 * @date 2012-2-25
 */
public class RequestUtil {

	/****
	 * 把request中所有的参数作为map返回，key为原来key值的小写形式
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map ToLowerMap(HttpServletRequest request) {
		Map rtnMap = new HashMap();
		Map allParams = request.getParameterMap();
		Set entries = allParams.entrySet();
		for (Iterator iterator = entries.iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String name = (String) entry.getKey();
			Object[] value = (Object[]) entry.getValue();

			if (value != null) {
				if (value.length == 1) {
					rtnMap.put(name.toLowerCase(), value[0]);
				} else {
					rtnMap.put(name.toLowerCase(), value);
				}
			}
		}
		return rtnMap;
	}

	/****
	 * 把request中所有的参数作为map返回，key为原来key值的大写形式
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object[]> ToUpperMap(HttpServletRequest request) {
		Map rtnMap = new HashMap();
		Map allParams = request.getParameterMap();
		Set entries = allParams.entrySet();
		for (Iterator iterator = entries.iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String name = (String) entry.getKey();
			Object[] value = (Object[]) entry.getValue();

			if (value != null) {
				if (value.length == 1) {
					rtnMap.put(name.toUpperCase(), value[0]);
				} else {
					rtnMap.put(name.toUpperCase(), value);
				}
			}
		}
		return rtnMap;
	}
}
