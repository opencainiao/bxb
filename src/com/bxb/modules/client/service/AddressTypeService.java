package com.bxb.modules.client.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.AddressTypeDao;
import com.bxb.modules.client.model.AddressType;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 系统常量类型服务实现
 * 
 * @author NBQ
 *
 */
@Service("addressTypeService")
public class AddressTypeService extends BaseService implements
		IAddressTypeService {

	@Resource(name = "addresstypedao")
	private AddressTypeDao addresstypedao;

	private static final Logger logger = LogManager
			.getLogger(AddressTypeService.class);

	@Override
	public AddressType findOneByIdObject(String _id) {

		return this.addresstypedao.findOneByIdObject(_id, AddressType.class);
	}

	@Override
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort,
			DBObject returnFields) {
		return this.addresstypedao.batchSearchPage(queryCondition, sort,
				returnFields);
	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields) {
		return this.addresstypedao
				.batchSearchOnePage(query, sort, returnFields);
	}

	@Override
	public String add(AddressType addresstype) {
		this.setCreateInfo(addresstype);
		return this.addresstypedao.insertObj(addresstype);
	}

	@Override
	public DBObject updatePart(DBObject returnFields, AddressType addresstype) {

		DBObject toUpdate = makeUpdate(addresstype);
		return this.addresstypedao.updateOneById(addresstype.get_id_str(),
				returnFields, toUpdate);
	}

	/****
	 * 转化对象为要更新的部分字段
	 * 
	 * @param update
	 * @return
	 */
	private DBObject makeUpdate(AddressType addresstype) {

		DBObject update = new BasicDBObject();
		DBObject updateSet = new BasicDBObject();

		updateSet
				.put("address_type_value", addresstype.getAddress_type_value());
		updateSet.put("address_type_name", addresstype.getAddress_type_name());

		this.setModifyInfo(updateSet);
		update.put("$set", updateSet);

		logger.debug("更新的对象信息\n{}", update);
		return update;
	}

	@Override
	public DBObject RemoveOneById(String _id) {
		return this.addresstypedao.findAndRemoveOneById(_id);
	}

	@Override
	public DBObject RemoveOneByIdLogic(String _id) {

		DBObject updateSet = new BasicDBObject();
		this.setModifyInfo(updateSet);
		return this.addresstypedao.findAndRemoveOneByIdLogic(_id, updateSet);
	}

	/****
	 * 查看是否已经存在同名的地址类型
	 */
	@Override
	public boolean isExistSameTypename(String address_type_name, String userid,
			String _id) {

		DBObject queryCondition = new BasicDBObject();
		// OR查询(名称、全拼或是首字母包含term的)
		queryCondition = new BasicDBObject();
		BasicDBList values = new BasicDBList();

		DBObject queryConditioncommon = new BasicDBObject();
		queryConditioncommon.put("address_type_name", address_type_name);
		queryConditioncommon.put("owner_user_id", userid);

		DBObject queryConditionpersonal = new BasicDBObject();
		queryConditionpersonal.put("address_type_name", address_type_name);
		queryConditionpersonal.put("owner_user_id", "system");

		values.add(queryConditionpersonal);
		values.add(queryConditioncommon);
		queryCondition.put("$or", values);

		queryCondition.put("_id", new BasicDBObject("$ne", new ObjectId(_id)));

		// 2.设置返回结果
		DBObject returnFields = new BasicDBObject();
		returnFields.put("address_type_name", 1);
		returnFields.put("owner_user_id", 1); // 不设置时，返回_id

		List<DBObject> types = this.addresstypedao.batchSearch(queryCondition,
				null, returnFields);

		if (types == null || types.isEmpty()) {
			return false;
		}

		return true;
	}

	/****
	 * 查看是否已经存在同名的地址类型
	 */
	@Override
	public boolean isExistSameTypename(String address_type_name, String userid) {

		DBObject queryCondition = new BasicDBObject();
		// OR查询(名称、全拼或是首字母包含term的)
		queryCondition = new BasicDBObject();
		BasicDBList values = new BasicDBList();

		DBObject queryConditioncommon = new BasicDBObject();
		queryConditioncommon.put("address_type_name", address_type_name);
		queryConditioncommon.put("owner_user_id", userid);

		DBObject queryConditionpersonal = new BasicDBObject();
		queryConditionpersonal.put("address_type_name", address_type_name);
		queryConditionpersonal.put("owner_user_id", "system");

		values.add(queryConditionpersonal);
		values.add(queryConditioncommon);
		queryCondition.put("$or", values);

		// 2.设置返回结果
		DBObject returnFields = new BasicDBObject();
		returnFields.put("address_type_name", 1);
		returnFields.put("owner_user_id", 1); // 不设置时，返回_id

		List<DBObject> types = this.addresstypedao.batchSearch(queryCondition,
				null, returnFields);

		if (types == null || types.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public AddressType findOneByWhere(DBObject query) {

		return this.addresstypedao.findOneByConditionObject(query,
				AddressType.class);
	}
}
