package com.bxb.modules.client.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.EmailDao;
import com.bxb.modules.client.model.Email;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 电话服务实现
 * 
 * @author NBQ
 *
 */
@Service("emailService")
public class EmailService extends BaseService implements IEmailService {

	@Resource(name = "emaildao")
	private EmailDao emaildao;

	private static final Logger logger = LogManager
			.getLogger(EmailService.class);

	@Override
	public Email findOneByIdObject(String _id) {

		return this.emaildao.findOneByIdObject(_id, Email.class);
	}

	@Override
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort,
			DBObject returnFields) {
		return this.emaildao
				.batchSearchPage(queryCondition, sort, returnFields);
	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields) {
		return this.emaildao.batchSearchOnePage(query, sort, returnFields);
	}

	@Override
	public String add(Email email) {
		this.setCreateInfo(email);
		return this.emaildao.insertObj(email);
	}

	@Override
	public DBObject updatePart(DBObject returnFields, Email email) {

		DBObject toUpdate = makeUpdate(email);
		return this.emaildao.updateOneById(email.get_id_str(), returnFields,
				toUpdate);
	}

	/****
	 * 转化对象为要更新的部分字段
	 * 
	 * @param update
	 * @return
	 */
	private DBObject makeUpdate(Email email) {

		DBObject update = new BasicDBObject();
		DBObject updateSet = new BasicDBObject();

		updateSet.put("type_value", email.getType_value());
		updateSet.put("type_name", email.getType_name());
		updateSet.put("email", email.getEmail());
		updateSet.put("mainflg", email.getMainflg());

		this.setModifyInfo(updateSet);
		update.put("$set", updateSet);

		logger.debug("更新的对象信息\n{}", update);
		return update;
	}

	@Override
	public DBObject RemoveOneById(String _id) {
		return this.emaildao.findAndRemoveOneById(_id);
	}

	@Override
	public DBObject RemoveOneByIdLogic(String _id) {

		DBObject updateSet = new BasicDBObject();
		this.setModifyInfo(updateSet);
		return this.emaildao.findAndRemoveOneByIdLogic(_id, updateSet);
	}

	@Override
	public Object findAllOnePageByOwnerId(String owner_id) {
		DBObject queryCondition = new BasicDBObject();
		queryCondition.put("owner_id", owner_id);

		DBObject sort = new BasicDBObject();
		sort.put("phone_type_value", 1);

		return batchSearchOnePage(queryCondition, sort, null);
	}

	@Override
	public List<String> add(List<Email> phones, String client_id) {

		// 1.删除已有的
		DBObject queryCondition = new BasicDBObject();
		queryCondition.put("owner_id", client_id);
		this.emaildao.remove(queryCondition);

		List<String> ids = new ArrayList<String>();

		for (Email email : phones) {
			email.setOwner_id(client_id);
			String addressid = this.add(email);

			ids.add(addressid);
		}

		return ids;
	}

}
