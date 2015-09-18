package com.bxb.modules.client.service.modifyclientinfo;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.ClientDao;
import com.bxb.modules.client.model.Client;
import com.bxb.modules.client.service.ClientService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 客户来源信息服务实现
 * 
 * @author NBQ
 *
 */
@Service("clientSourceInfoService")
public class ClientSourceInfoService extends BaseService implements
		IModifyClientInfoService {

	@Resource(name = "clientdao")
	private ClientDao clientdao;

	private static final Logger logger = LogManager
			.getLogger(ClientSourceInfoService.class);

	@Override
	public DBObject updatePart(DBObject returnFields, Client client) {

		DBObject toUpdate = makeUpdate(client);
		DBObject updatedResult = this.clientdao.updateOneById(
				client.get_id_m(), returnFields, toUpdate);

		return updatedResult;
	}

	/****
	 * 转化对象为要更新的部分字段
	 * 
	 * @param update
	 * @return
	 */
	private DBObject makeUpdate(Client client) {

		DBObject update = new BasicDBObject();
		DBObject updateSet = new BasicDBObject();

		updateSet.put("source_type", client.getSource_type());
		updateSet.put("introducer_name", client.getIntroducer_name());
		updateSet.put("introducer_relationship",
				client.getIntroducer_relationship());
		updateSet.put("introducer_closeness", client.getIntroducer_closeness());
		updateSet.put("introducer_evaluation",
				client.getIntroducer_evaluation());
		updateSet.put("contact_type", client.getContact_type());
		updateSet.put("contact_attention", client.getContact_attention());

		this.setModifyInfoWithUserId(updateSet, client.getOwner_user_id());
		update.put("$set", updateSet);

		logger.debug(client);
		logger.debug(update);
		return update;
	}
}
