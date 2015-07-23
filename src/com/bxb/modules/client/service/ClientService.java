package com.bxb.modules.client.service;

import java.text.ParseException;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.StringUtil;
import org.springframework.stereotype.Service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.common.util.AgeUtil;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.ClientDao;
import com.bxb.modules.client.model.Client;
import com.bxb.modules.infrastructure.enums.SysConstTypeEnum;
import com.bxb.modules.infrastructure.service.ISysConstService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 用户服务实现
 * 
 * @author NBQ
 *
 */
@Service("clientService")
public class ClientService extends BaseService implements IClientService {

	@Resource(name = "clientdao")
	private ClientDao clientdao;

	@Resource(name = "sysConstService")
	private ISysConstService sysConstService;

	private static final Logger logger = LogManager
			.getLogger(ClientService.class);

	@Override
	public Client findOneByIdObject(String _id) {

		return this.clientdao.findOneByIdObject(_id, Client.class);
	}

	@Override
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort,
			DBObject returnFields) {
		return this.clientdao.batchSearchPage(queryCondition, sort,
				returnFields);
	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields) {
		return this.clientdao.batchSearchOnePage(query, sort, returnFields);
	}

	@Override
	public String add(Client client) {

		setClientInf(client);
		this.setCreateInfo(client);
		return this.clientdao.insertObj(client);
	}

	private void setClientInf(Client client) {

		String education_type = client.getEducation_type();
		if (StringUtil.isNotEmpty(education_type)) {
			String education_type_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.EDUCATION_TYPE.getCode(),
							education_type);

			client.setEducation_type_name(education_type_name);
		}

		String birth_date = client.getBirth_date();
		if (StringUtil.isEmpty(birth_date)) {
			try {
				client.setAge(AgeUtil.getAge(birth_date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public DBObject updatePart(DBObject returnFields, Client client) {

		DBObject toUpdate = makeUpdate(client);
		return this.clientdao.updateOneById(client.get_id_str(), returnFields,
				toUpdate);
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

		// updateSet.put("typename", client.getTypename());

		this.setModifyInfo(updateSet);
		update.put("$set", updateSet);

		logger.debug("更新的对象信息\n{}", update);
		return update;
	}

	@Override
	public DBObject RemoveOneById(String _id) {
		return this.clientdao.findAndRemoveOneById(_id);
	}

	@Override
	public DBObject RemoveOneByIdLogic(String _id) {

		DBObject updateSet = new BasicDBObject();
		this.setModifyInfo(updateSet);
		return this.clientdao.findAndRemoveOneByIdLogic(_id, updateSet);
	}

}
