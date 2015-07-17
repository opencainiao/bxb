package com.bxb.modules.client.service.util;

import org.bson.types.ObjectId;
import org.mou.common.StringUtil;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CommonTypeSearchUtil {

	/****
	 * 取查询条件对象<br>
	 * 1. 主键条件：<br>
	 * 1.1 如果_id不为空，则主键不为_id <br>
	 * 1.2 如果_id为空，则不设置主键条件<br>
	 * 2. 所属用户为系统内置或owner_user_id <br>
	 * 3. 名称为type_name
	 * 
	 * @param type_name
	 * @param owner_user_id
	 * @param _id
	 * @return
	 */
	public static DBObject getQuerySameTypeName(String type_name,
			String owner_user_id, String _id) {

		DBObject queryCondition = new BasicDBObject();
		// OR查询(名称、全拼或是首字母包含term的)
		queryCondition = new BasicDBObject();
		BasicDBList values = new BasicDBList();

		DBObject queryConditioncommon = new BasicDBObject();
		queryConditioncommon.put("owner_user_id", "system");

		DBObject queryConditionpersonal = new BasicDBObject();
		queryConditionpersonal.put("owner_user_id", owner_user_id);

		values.add(queryConditionpersonal);
		values.add(queryConditioncommon);
		queryCondition.put("$or", values);

		queryCondition.put("type_name", type_name.trim());

		if (StringUtil.isNotEmpty(_id)) {
			queryCondition.put("_id", new BasicDBObject("$ne",
					new ObjectId(_id)));
		}

		return queryCondition;
	}

	/****
	 * 取查询条件对象<br>
	 * 1. 主键条件：<br>
	 * 1.1 如果_id不为空，则主键不为_id <br>
	 * 1.2 如果_id为空，则不设置主键条件<br>
	 * 2. 所属用户为系统内置或owner_user_id <br>
	 * 3. 值为type_value
	 * 
	 * @param type_value
	 * @param owner_user_id
	 * @param _id
	 * @return
	 */
	public static DBObject getQuerySameTypeValue(String type_value,
			String owner_user_id, String _id) {

		DBObject queryCondition = new BasicDBObject();
		// OR查询(名称、全拼或是首字母包含term的)
		queryCondition = new BasicDBObject();
		BasicDBList values = new BasicDBList();

		DBObject queryConditioncommon = new BasicDBObject();
		queryConditioncommon.put("owner_user_id", "system");

		DBObject queryConditionpersonal = new BasicDBObject();
		queryConditionpersonal.put("owner_user_id", owner_user_id);

		values.add(queryConditionpersonal);
		values.add(queryConditioncommon);
		queryCondition.put("$or", values);

		queryCondition.put("type_value", type_value.trim());

		if (StringUtil.isNotEmpty(_id)) {
			queryCondition.put("_id", new BasicDBObject("$ne",
					new ObjectId(_id)));
		}

		return queryCondition;
	}

	public static DBObject getReturnFields() {
		DBObject returnFields = new BasicDBObject();
		
		returnFields.put("type_name", 1);
		returnFields.put("type_value", 1);
		returnFields.put("owner_user_id", 1);

		return returnFields;
	}
}
