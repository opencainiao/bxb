package com.bxb.modules.client.service.modifyclientinfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.ClientDao;
import com.bxb.modules.client.model.Client;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 客户服务信息服务实现
 * 
 * @author NBQ
 *
 */
@Service("clientServiceInfoService")
public class ClientServiceInfoService extends BaseService implements
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

		updateSet.put("interesting_service", client.getInteresting_service());

		this.setModifyInfoWithUserId(updateSet, client.getOwner_user_id());
		update.put("$set", updateSet);

		return update;
	}
}
