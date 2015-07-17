package com.bxb.modules.client.service;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.PhoneDao;
import com.bxb.modules.client.model.Phone;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 电话服务实现
 * 
 * @author NBQ
 *
 */
@Service("phoneService")
public class PhoneService extends BaseService implements IPhoneService {

	@Resource(name = "phonedao")
	private PhoneDao phonedao;

	private static final Logger logger = LogManager
			.getLogger(PhoneService.class);

	@Override
	public Phone findOneByIdObject(String _id) {

		return this.phonedao.findOneByIdObject(_id, Phone.class);
	}

	@Override
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort,
			DBObject returnFields) {
		return this.phonedao
				.batchSearchPage(queryCondition, sort, returnFields);
	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields) {
		return this.phonedao.batchSearchOnePage(query, sort, returnFields);
	}

	@Override
	public String add(Phone phone) {
		this.setCreateInfo(phone);
		return this.phonedao.insertObj(phone);
	}

	@Override
	public DBObject updatePart(DBObject returnFields, Phone phone) {

		DBObject toUpdate = makeUpdate(phone);
		return this.phonedao.updateOneById(phone.get_id_str(), returnFields,
				toUpdate);
	}

	/****
	 * 转化对象为要更新的部分字段
	 * 
	 * @param update
	 * @return
	 */
	private DBObject makeUpdate(Phone phone) {

		DBObject update = new BasicDBObject();
		DBObject updateSet = new BasicDBObject();

		updateSet.put("type_value", phone.getType_value());
		updateSet.put("type_name", phone.getType_name());
		updateSet.put("phone_number", phone.getPhone_number());
		updateSet.put("mainflg", phone.getMainflg());

		this.setModifyInfo(updateSet);
		update.put("$set", updateSet);

		logger.debug("更新的对象信息\n{}", update);
		return update;
	}

	@Override
	public DBObject RemoveOneById(String _id) {
		return this.phonedao.findAndRemoveOneById(_id);
	}

	@Override
	public DBObject RemoveOneByIdLogic(String _id) {

		DBObject updateSet = new BasicDBObject();
		this.setModifyInfo(updateSet);
		return this.phonedao.findAndRemoveOneByIdLogic(_id, updateSet);
	}

	@Override
	public Object findAllOnePageByOwnerId(String owner_id) {
		DBObject queryCondition = new BasicDBObject();
		queryCondition.put("owner_id", owner_id);

		DBObject sort = new BasicDBObject();
		sort.put("phone_type_value", 1);

		return batchSearchOnePage(queryCondition, sort, null);
	}

}
