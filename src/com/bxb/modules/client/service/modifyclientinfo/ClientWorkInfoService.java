package com.bxb.modules.client.service.modifyclientinfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.ClientDao;
import com.bxb.modules.client.model.Client;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 客户工作信息服务实现
 * 
 * @author NBQ
 *
 */
@Service("clientWorkInfoService")
public class ClientWorkInfoService extends BaseService implements
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

		updateSet.put("company", client.getCompany());
		updateSet.put("company_nature", client.getCompany_nature());
		updateSet.put("trade_type", client.getTrade_type());
		updateSet.put("career_type", client.getCareer_type());
		updateSet.put("job_position", client.getJob_position());
		updateSet.put("job_level", client.getJob_level());

		this.setModifyInfoWithUserId(updateSet, client.getOwner_user_id());
		update.put("$set", updateSet);

		return update;
	}
}
