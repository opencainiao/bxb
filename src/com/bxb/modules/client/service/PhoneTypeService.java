package com.bxb.modules.client.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.PhoneTypeDao;
import com.bxb.modules.client.model.CommonType;
import com.bxb.modules.client.service.util.CommonTypeSearchUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 手机类型服务实现
 * 
 * @author NBQ
 *
 */
@Service("phoneTypeService")
public class PhoneTypeService extends BaseService implements IPhoneTypeService {

	@Resource(name = "phonetypedao")
	private PhoneTypeDao phonetypedao;

	private static final Logger logger = LogManager
			.getLogger(PhoneTypeService.class);

	@Override
	public CommonType findOneByIdObject(String _id) {

		return this.phonetypedao.findOneByIdObject(_id, CommonType.class);
	}

	@Override
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort,
			DBObject returnFields) {
		return this.phonetypedao.batchSearchPage(queryCondition, sort,
				returnFields);
	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields) {
		return this.phonetypedao.batchSearchOnePage(query, sort, returnFields);
	}

	@Override
	public String add(CommonType phonetype) {
		this.setCreateInfo(phonetype);
		return this.phonetypedao.insertObj(phonetype);
	}

	@Override
	public DBObject updatePart(DBObject returnFields, CommonType phonetype) {

		DBObject toUpdate = makeUpdate(phonetype);
		return this.phonetypedao.updateOneById(phonetype.get_id_str(),
				returnFields, toUpdate);
	}

	/****
	 * 转化对象为要更新的部分字段
	 * 
	 * @param update
	 * @return
	 */
	private DBObject makeUpdate(CommonType phonetype) {

		DBObject update = new BasicDBObject();
		DBObject updateSet = new BasicDBObject();

		updateSet.put("type_value", phonetype.getType_value());
		updateSet.put("type_name", phonetype.getType_name());

		this.setModifyInfo(updateSet);
		update.put("$set", updateSet);

		logger.debug("更新的对象信息\n{}", update);
		return update;
	}

	@Override
	public DBObject RemoveOneById(String _id) {
		return this.phonetypedao.findAndRemoveOneById(_id);
	}

	@Override
	public DBObject RemoveOneByIdLogic(String _id) {

		DBObject updateSet = new BasicDBObject();
		this.setModifyInfo(updateSet);
		return this.phonetypedao.findAndRemoveOneByIdLogic(_id, updateSet);
	}

	@Override
	public CommonType findOneByWhere(DBObject query) {
		return this.phonetypedao.findOneByConditionObject(query,
				CommonType.class);
	}

	@Override
	public boolean isExistSameTypename(String type_name, String owner_user_id,
			String _id) {

		DBObject queryCondition = CommonTypeSearchUtil.getQuerySameTypeName(
				type_name, owner_user_id, _id);

		DBObject returnFields = CommonTypeSearchUtil.getReturnFields();

		List<DBObject> types = this.phonetypedao.findBatchDbOjbect(queryCondition,
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

		List<DBObject> types = this.phonetypedao.findBatchDbOjbect(queryCondition,
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

		List<DBObject> types = this.phonetypedao.findBatchDbOjbect(queryCondition,
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

		List<DBObject> types = this.phonetypedao.findBatchDbOjbect(queryCondition,
				null, returnFields);

		if (types == null || types.isEmpty()) {
			return false;
		}

		return true;
	}

}
