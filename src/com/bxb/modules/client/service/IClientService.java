package com.bxb.modules.client.service;

import java.util.List;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.client.model.Client;
import com.mongodb.DBObject;

/****
 * 用户服务接口
 * 
 * @author NBQ
 *
 */
public interface IClientService {

	/****
	 * 根据条件，查询一个客户
	 * 
	 * @param query
	 * @param isRedisplay
	 * @return
	 */
	public Client findOneByCondition(DBObject query, boolean isRedisplay);

	/****
	 * 根据条件，查询一个客户(不回显)
	 * @param query
	 * @return
	 */
	public DBObject findOneByConditionDBObj(DBObject query);

	/****
	 * 根据id，查询一个对象
	 * 
	 * @param _id
	 * @param isRedisplay
	 *            true-进行回显 false-不回显
	 * @return
	 */
	public Client findOneByIdObject(String _id, boolean isRedisplay);

	/****
	 * 条件查询，分页
	 * 
	 * @param query
	 * @param sort
	 * @param returnFields
	 * @return
	 */
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort,
			DBObject returnFields);

	/****
	 * 查询用户的所有客户
	 * 
	 * @param userId
	 * @return
	 */
	public List<DBObject> findAllClientsByUserId(String userId);

	/****
	 * 条件查询，1页，查询所有
	 * 
	 * @param query
	 * @param sort
	 * @param returnFields
	 * @return
	 */
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields);

	/****
	 * 插入对象，返回插入后的生成的ObjectId
	 * 
	 * @param client
	 * @return
	 */
	public String add(Client client);

	/****
	 * 根据客户名，取客户id
	 * 
	 * @param clientName
	 * @param userId
	 * @return
	 */
	public String getOidByClientName(String clientName, String userId);

	/****
	 * 更新一条记录，返回更新后的结果，根据对象的主键ObjectId
	 * 
	 * @param returnFields
	 * @param client
	 * @return
	 */
	public DBObject updatePart(DBObject returnFields, Client client);

	/****
	 * 查询并删除一条记录
	 * 
	 * @param _id
	 * @return
	 */
	public DBObject RemoveOneById(String _id);

	/****
	 * 查询并删除一条记录(逻辑删除)<br>
	 * 
	 * delflg - 1 已删除 0 未删除
	 * 
	 * @param _id
	 * @return
	 */
	public DBObject RemoveOneByIdLogic(String _id);

}
