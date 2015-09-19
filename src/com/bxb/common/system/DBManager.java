package com.bxb.common.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.StringUtil;

import com.mongodb.DB;
import com.mongodb.DBCollection;

import mou.mongodb.MongoClientManager;

public class DBManager {

	private static String dbName;
	private static final Logger logger = LogManager.getLogger(DBManager.class);

	/****
	 * 初始化数据库，只一次
	 * 
	 * @param dbName
	 */
	public static void initDB(String dbName) {

		if (StringUtil.isEmpty(dbName)) {
			return;
		}

		if (DBManager.dbName != null) {
			return;
		}

		try {
			DBManager.dbName = dbName;
			MongoClientManager.init(dbName);
//			MongoClientManager.init("182.92.238.54", dbName);
		} catch (Exception e) {
			logger.error(StringUtil.getStackTrace(e));
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static DB getDB(){
		return MongoClientManager.getDb();
	}

	/****
	 * 取数据库的Collection
	 * 
	 * @param collectionName
	 * @return
	 */
	public static DBCollection getCollection(String collectionName) {
		return MongoClientManager.getCollection(collectionName);
	}

	/****
	 * 取Collection的条数
	 * 
	 * @param collectionName
	 * @return
	 */
	public static long getCollectionSize(String collectionName) {
		DBCollection collection = getCollection(collectionName);
		return collection.getCount();
	}
}
