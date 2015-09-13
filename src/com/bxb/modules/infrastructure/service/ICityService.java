package com.bxb.modules.infrastructure.service;

import java.util.List;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.infrastructure.model.City;
import com.mongodb.DBObject;

/****
 * 城市服务接口
 * 
 * @author NBQ
 *
 */
public interface ICityService {

	/****
	 * 根据编码查询名称
	 * @param code
	 * @return
	 */
	public String findNameById(int id);

	/****
	 * 根据id，查询一个对象
	 * 
	 * @param _id
	 * @return
	 */
	public City findOneByIdObject(String _id);

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
	 * @param city
	 * @return
	 */
	public String add(City city);

	/****
	 * 更新一条记录，返回更新后的结果，根据对象的主键ObjectId
	 * 
	 * @param returnFields
	 * @param city
	 * @return
	 */
	public DBObject updatePart(DBObject returnFields, City city);

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

	/****
	 * 查询节点的所有直接子节点
	 * 
	 * @param sort
	 * @param returnFields
	 * @param parentId
	 * @return
	 */
	public PageVO findChildrenByPIdOnePage(DBObject sort, DBObject returnFields, Integer parentId);

	/****
	 * 查询节点的所有直接子节点
	 * 
	 * @param sort
	 * @param returnFields
	 * @param parentId
	 * @return
	 */
	public List<DBObject> findChildrenByPId(DBObject sort, DBObject returnFields, Integer parentId);

}
