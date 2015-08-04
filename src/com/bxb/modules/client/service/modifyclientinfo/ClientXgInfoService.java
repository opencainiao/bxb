package com.bxb.modules.client.service.modifyclientinfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.ClientDao;
import com.bxb.modules.client.model.Client;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 客户性格信息服务实现
 * 
 * @author NBQ
 *
 */
@Service("clientXgInfoService")
public class ClientXgInfoService extends BaseService implements
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

		updateSet.put("birth_ages", client.getBirth_ages());
		updateSet.put("age_group", client.getAge_group());
		updateSet.put("constellation", client.getConstellation());
		updateSet.put("blood_group", client.getBlood_group());
		updateSet.put("temperament_type", client.getTemperament_type());
		updateSet.put("pdp_type", client.getPdp_type());
		updateSet.put("hobbies", client.getHobbies());

		this.setModifyInfoWithUserId(updateSet, client.getOwner_user_id());
		update.put("$set", updateSet);

		return update;
	}
}
