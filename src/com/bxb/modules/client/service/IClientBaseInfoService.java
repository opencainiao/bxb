package com.bxb.modules.client.service;

import com.bxb.modules.client.model.Client;
import com.mongodb.DBObject;

/****
 * 客户基本信息服务接口
 * 
 * @author NBQ
 *
 */
public interface IClientBaseInfoService {

	/****
	 * 更新一条记录，返回更新后的结果，根据对象的主键ObjectId
	 * 
	 * @param returnFields
	 * @param client
	 * @return
	 */
	public DBObject updatePart(DBObject returnFields, Client client);
}
