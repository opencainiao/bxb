package com.bxb.modules.infrastructure.service;

import java.util.List;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.infrastructure.model.SysConst;
import com.mongodb.DBObject;

/****
 * 常量类型服务接口
 * 
 * @author NBQ
 *
 */
public interface ISysConstService {

	/****
	 * 根据id，查询一个对象
	 * 
	 * @param _id
	 * @return
	 */
	public SysConst findOneByIdObject(String _id);

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
	 * @param sysconst
	 * @return
	 */
	public String add(SysConst sysconst);

	/****
	 * 更新一条记录，返回更新后的结果，根据对象的主键ObjectId
	 * 
	 * @param returnFields
	 * @param sysconst
	 * @return
	 */
	public DBObject updatePart(DBObject returnFields, SysConst sysconst);

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
	 * 是否存在相同的常量值
	 * 
	 * @param sysconst
	 * @return
	 */
	public boolean isExistSameConstval(SysConst sysconst);

	/****
	 * 是否存在相同的常量显示值
	 * 
	 * @param sysconst
	 * @return
	 */
	public boolean isExistSameConstDispval(SysConst sysconst);

	/****
	 * 查询常量类型下的所有常量
	 * 
	 * @param typecode
	 * @return
	 */
	public List<DBObject> findAllConstBySysconstTypecode(String typecode);

	/****
	 * 根据常量类型，查询下面所有的常量
	 * 
	 * @param typecode
	 * @param sort
	 * @param returnFields
	 * @return
	 */
	public Object findSysconstByConstTypeOnePage(String typecode, DBObject sort, DBObject returnFields);

	/****
	 * 根据类型和值，查询显示值
	 * 
	 * @return
	 */
	public String findDispValByTypecodAndVal(String typecode, String val);

	/****
	 * 根据常量类型，查询下面所有的常量
	 * 
	 * @param typecode
	 * @param sort
	 * @param returnFields
	 * @return
	 */
	public List<DBObject> findSysconstByConstType(String typecode, DBObject sort, DBObject returnFields);

}
