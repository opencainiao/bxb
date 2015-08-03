package com.bxb.modules.client.service.modifyclientinfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.ClientDao;
import com.bxb.modules.client.model.Client;
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
		updateSet.put("introducer_relationship", client.getIntroducer_relationship());
		updateSet.put("introducer_closeness", client.getIntroducer_closeness());
		updateSet.put("introducer_evaluation", client.getIntroducer_evaluation());

		this.setModifyInfoWithUserId(updateSet, client.getOwner_user_id());
		update.put("$set", updateSet);

		return update;
	}
}
