package com.bxb.modules.client.service.modifyclientinfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.ClientDao;
import com.bxb.modules.client.model.Client;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 客户收入信息服务实现
 * 
 * @author NBQ
 *
 */
@Service("clientIncomeInfoService")
public class ClientIncomeInfoService extends BaseService implements IModifyClientInfoService {

	@Resource(name = "clientdao")
	private ClientDao clientdao;

	@Override
	public DBObject updatePart(DBObject returnFields, Client client) {

		DBObject toUpdate = makeUpdate(client);
		DBObject updatedResult = this.clientdao.updateOneById(client.get_id_m(), returnFields, toUpdate);

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

		updateSet.put("annual_income_personal", client.getAnnual_income_personal());
		updateSet.put("annual_income_personal_type", client.getAnnual_income_personal_type());
		updateSet.put("annual_income_family", client.getAnnual_income_family());
		updateSet.put("annual_income_family_type", client.getAnnual_income_family_type());
		updateSet.put("family_income_feature", client.getFamily_income_feature());
		updateSet.put("family_financial_standing", client.getFamily_financial_standing());

		this.setModifyInfoWithUserId(updateSet, client.getOwner_user_id());
		update.put("$set", updateSet);

		return update;
	}

}
