package com.bxb.modules.infrastructure.service.impl;

import javax.annotation.Resource;

import org.mou.common.StringUtil;
import org.springframework.stereotype.Service;

import com.bxb.modules.infrastructure.dao.AutoIncreaserDao;
import com.bxb.modules.infrastructure.service.IAutoIncreaserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service("autoIncreaserService")
public class AutoIncreaserService implements IAutoIncreaserService {

	@Resource(name = "autoIncreaserDao")
	private AutoIncreaserDao autoIncreaserDao;

	@Override
	public int getAutoIncreasedInteger(String mainkey) {

		DBObject queryCondition = new BasicDBObject();
		queryCondition.put("mainkey", mainkey);// 自增标识

		DBObject returnFields = new BasicDBObject();
		returnFields.put("val", 1);// 自增值

		DBObject update = new BasicDBObject();
		update.put("$inc", new BasicDBObject("val", 1));

		DBObject result = autoIncreaserDao.updateOneByCondition(queryCondition,
				returnFields, update, true);

		return (Integer) (result.get("val"));
	}

	@Override
	public String getAutoIncreaseString(String mainkey) {

		DBObject queryCondition = new BasicDBObject();
		queryCondition.put("mainkey", mainkey);// 自增标识

		DBObject returnFields = new BasicDBObject();
		returnFields.put("val", 1);// 自增值
		returnFields.put("length", 1);// 自增值

		DBObject update = new BasicDBObject();
		update.put("$inc", new BasicDBObject("val", 1));

		DBObject result = autoIncreaserDao.updateOneByCondition(queryCondition,
				returnFields, update, true);

		int value = (Integer) (result.get("val"));
		int length = result.get("length") == null ? 8 : (Integer) (result
				.get("length"));

		return StringUtil.addCharL(value, length, "0");
	}

}
