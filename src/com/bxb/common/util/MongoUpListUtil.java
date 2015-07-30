package com.bxb.common.util;

import java.util.List;

import mou.mongodb.util.JsonUtil;

import com.mongodb.util.JSON;

/****
 * 更新时，对数组型对象做转换（为了去掉值为空的key）
 * 
 * @author NBQ
 *
 */
public class MongoUpListUtil {

	@SuppressWarnings("rawtypes")
	public static Object getUpObject(List list) {
		String jsonStr = JsonUtil.toJsonStr(list);
		Object rtnObj = JSON.parse(jsonStr);

		return rtnObj;
	}
}
