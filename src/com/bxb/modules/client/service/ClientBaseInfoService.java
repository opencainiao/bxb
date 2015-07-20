package com.bxb.modules.client.service;

import java.text.ParseException;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bxb.common.util.AgeUtil;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.ClientBaseInfoDao;
import com.bxb.modules.client.model.ClientBaseInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 客户基本信息服务实现
 * 
 * @author NBQ
 *
 */
@Service("clientBaseInfoService")
public class ClientBaseInfoService extends BaseService implements
		IClientBaseInfoService {

	@Resource(name = "clientbaseinfodao")
	private ClientBaseInfoDao clientbaseinfodao;

	private static final Logger logger = LogManager
			.getLogger(ClientBaseInfoService.class);

	@Override
	public DBObject updatePart(DBObject returnFields,
			ClientBaseInfo clientbaseinfo) {

		DBObject toUpdate = makeUpdate(clientbaseinfo);
		return this.clientbaseinfodao.updateOneById(
				clientbaseinfo.get_id_str(), returnFields, toUpdate);
	}

	/****
	 * 转化对象为要更新的部分字段
	 * 
	 * @param update
	 * @return
	 */
	private DBObject makeUpdate(ClientBaseInfo clientbaseinfo) {

		DBObject update = new BasicDBObject();
		DBObject updateSet = new BasicDBObject();

		updateSet.put("client_name", clientbaseinfo.getClient_name());
		updateSet.put("sex", clientbaseinfo.getSex());
		updateSet.put("id_number", clientbaseinfo.getId_number());
		updateSet.put("birth_date", clientbaseinfo.getBirth_date());
		try {
			updateSet.put("age", AgeUtil.getAge(clientbaseinfo.getAge()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		updateSet.put("region_code", clientbaseinfo.getRegion_code());
		updateSet.put("region_name", clientbaseinfo.getRegion_name());
		updateSet.put("region_type", clientbaseinfo.getRegion_type());
		updateSet.put("education_type", clientbaseinfo.getEducation_type());
		updateSet.put("name_card_id", clientbaseinfo.getName_card_id());

		this.setModifyInfo(updateSet);
		update.put("$set", updateSet);

		logger.debug("更新的对象信息\n{}", update);
		return update;
	}

	@Override
	public ClientBaseInfo findOneByIdObject(String _id) {

		return this.clientbaseinfodao.findOneByIdObject(_id,
				ClientBaseInfo.class);
	}
}
