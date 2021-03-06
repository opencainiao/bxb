package com.bxb.modules.client.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.EmailTypeDao;
import com.bxb.modules.client.model.CommonType;
import com.bxb.modules.client.service.util.CommonTypeSearchUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 系统常量类型服务实现
 * 
 * @author NBQ
 *
 */
@Service("emailTypeService")
public class EmailTypeService extends BaseService implements IEmailTypeService {

	@Resource(name = "emailtypedao")
	private EmailTypeDao emailtypedao;

	private static final Logger logger = LogManager
			.getLogger(EmailTypeService.class);

	@Override
	public CommonType findOneByIdObject(String _id) {

		return this.emailtypedao.findOneByIdObject(_id, CommonType.class);
	}

	@Override
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort,
			DBObject returnFields) {
		return this.emailtypedao.batchSearchPage(queryCondition, sort,
				returnFields);
	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields) {
		return this.emailtypedao.batchSearchOnePage(query, sort, returnFields);
	}

	@Override
	public String add(CommonType addresstype) {
		this.setCreateInfo(addresstype);
		return this.emailtypedao.insertObj(addresstype);
	}

	@Override
	public DBObject updatePart(DBObject returnFields, CommonType addresstype) {

		DBObject toUpdate = makeUpdate(addresstype);
		return this.emailtypedao.updateOneById(addresstype.get_id_m(),
				returnFields, toUpdate);
	}

	/****
	 * 转化对象为要更新的部分字段
	 * 
	 * @param update
	 * @return
	 */
	private DBObject makeUpdate(CommonType addresstype) {

		DBObject update = new BasicDBObject();
		DBObject updateSet = new BasicDBObject();

		updateSet.put("type_value", addresstype.getType_value());
		updateSet.put("type_name", addresstype.getType_name());

		this.setModifyInfo(updateSet);
		update.put("$set", updateSet);

		logger.debug("更新的对象信息\n{}", update);
		return update;
	}

	@Override
	public DBObject RemoveOneById(String _id) {
		return this.emailtypedao.findAndRemoveOneById(_id);
	}

	@Override
	public DBObject RemoveOneByIdLogic(String _id) {

		DBObject updateSet = new BasicDBObject();
		this.setModifyInfo(updateSet);
		return this.emailtypedao.findAndRemoveOneByIdLogic(_id, updateSet);
	}

	@Override
	public boolean isExistSameTypename(String type_name, String owner_user_id,
			String _id) {

		DBObject queryCondition = CommonTypeSearchUtil.getQuerySameTypeName(
				type_name, owner_user_id, _id);

		DBObject returnFields = CommonTypeSearchUtil.getReturnFields();

		List<DBObject> types = this.emailtypedao.findBatchDbOjbect(queryCondition,
				null, returnFields);

		if (types == null || types.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isExistSameTypename(String type_name, String owner_user_id) {

		DBObject queryCondition = CommonTypeSearchUtil.getQuerySameTypeName(
				type_name, owner_user_id, null);

		DBObject returnFields = CommonTypeSearchUtil.getReturnFields();

		List<DBObject> types = this.emailtypedao.findBatchDbOjbect(queryCondition,
				null, returnFields);

		if (types == null || types.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isExistSameTypeValue(String type_value, String owner_user_id) {

		DBObject queryCondition = CommonTypeSearchUtil.getQuerySameTypeValue(
				type_value, owner_user_id, null);

		DBObject returnFields = CommonTypeSearchUtil.getReturnFields();

		List<DBObject> types = this.emailtypedao.findBatchDbOjbect(queryCondition,
				null, returnFields);

		if (types == null || types.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isExistSameTypeValue(String type_value,
			String owner_user_id, String _id) {

		DBObject queryCondition = CommonTypeSearchUtil.getQuerySameTypeName(
				type_value, owner_user_id, _id);

		DBObject returnFields = CommonTypeSearchUtil.getReturnFields();

		List<DBObject> types = this.emailtypedao.findBatchDbOjbect(queryCondition,
				null, returnFields);

		if (types == null || types.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public CommonType findOneByWhere(DBObject query) {

		return this.emailtypedao.findOneByConditionObject(query,
				CommonType.class);
	}
}
