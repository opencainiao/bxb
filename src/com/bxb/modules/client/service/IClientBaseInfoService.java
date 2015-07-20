package com.bxb.modules.client.service;

import com.bxb.modules.client.model.ClientBaseInfo;
import com.mongodb.DBObject;

/****
 * 客户基本信息服务接口
 * 
 * @author NBQ
 *
 */
public interface IClientBaseInfoService {

	/****
	 * 根据id，查询一个对象
	 * 
	 * @param _id
	 * @return
	 */
	public ClientBaseInfo findOneByIdObject(String _id);

	/****
	 * 更新一条记录，返回更新后的结果，根据对象的主键ObjectId
	 * 
	 * @param returnFields
	 * @param clientbaseinfo
	 * @return
	 */
	public DBObject updatePart(DBObject returnFields,
			ClientBaseInfo clientbaseinfo);
}
