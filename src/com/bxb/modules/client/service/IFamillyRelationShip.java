package com.bxb.modules.client.service;

import java.util.List;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.client.model.Address;
import com.bxb.modules.infrastructure.model.ClientRelationShip;
import com.mongodb.DBObject;

/****
 * 家庭关系服务接口
 * 
 * @author NBQ
 *
 */
public interface IFamillyRelationShip {

	/****
	 * 查询一个人的所有地址信息
	 * 
	 * @param ownerId
	 * @return
	 */
	public List<DBObject> findAllByOwnerId(String ownerId);

	/****
	 * 条件查询，1页，查询所有
	 * 
	 * @param query
	 * @param sort
	 * @param returnFields
	 * @return
	 */
	public PageVO findAllOnePageByOwnerId(String ownerId);

	/****
	 * 根据id，查询一个对象
	 * 
	 * @param _id
	 * @return
	 */
	public Address findOneByIdObject(String _id);

	/****
	 * 条件查询，分页
	 * 
	 * @param query
	 * @param sort
	 * @param returnFields
	 * @return
	 */
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort, DBObject returnFields);

	/****
	 * 条件查询，1页，查询所有
	 * 
	 * @param query
	 * @param sort
	 * @param returnFields
	 * @return
	 */
	public PageVO batchSearchOnePage(DBObject query, DBObject sort, DBObject returnFields);

	/****
	 * 插入对象，返回插入后的生成的ObjectId
	 * 
	 * @param address
	 * @return
	 */
	public String add(ClientRelationShip clientrelationship);

	/****
	 * 更新一条记录，返回更新后的结果，根据对象的主键ObjectId
	 * 
	 * @param returnFields
	 * @param address
	 * @return
	 */
	public DBObject updatePart(DBObject returnFields, Address address);

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
