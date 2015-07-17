package com.bxb.modules.client.service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.client.model.CommonType;
import com.mongodb.DBObject;

/****
 * 手机类型服务接口
 * 
 * @author NBQ
 *
 */
public interface IPhoneTypeService {

	/****
	 * 根据条件，查询一个对象
	 * 
	 * @param _id
	 * @return
	 */
	public CommonType findOneByWhere(DBObject query);

	/****
	 * 根据id，查询一个对象
	 * 
	 * @param _id
	 * @return
	 */
	public CommonType findOneByIdObject(String _id);

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
	 * @param phonetype
	 * @return
	 */
	public String add(CommonType phonetype);

	/****
	 * 更新一条记录，返回更新后的结果，根据对象的主键ObjectId
	 * 
	 * @param returnFields
	 * @param phonetype
	 * @return
	 */
	public DBObject updatePart(DBObject returnFields, CommonType phonetype);

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
	 * 是否存在相同名字的类型(_id不是_id)
	 * 
	 * @param type_name
	 * @param userid
	 * @param _id
	 * @return
	 */
	public boolean isExistSameTypename(String type_name, String userid,
			String _id);

	/****
	 * 是否存在相同名字的类型
	 * 
	 * @param type_name
	 * @param owner_user_id
	 * @return
	 */
	public boolean isExistSameTypename(String type_name, String owner_user_id);

	/****
	 * 是否存在相同值的类型(_id不是_id)
	 * 
	 * @param type_value
	 * @param owner_user_id
	 * @param _id
	 * @return
	 */
	boolean isExistSameTypeValue(String type_value, String owner_user_id,
			String _id);

	/****
	 * 判断是否存在同样值的类型
	 * 
	 * @param type_value
	 * @param owner_user_id
	 * @return
	 */
	boolean isExistSameTypeValue(String type_value, String owner_user_id);
}
