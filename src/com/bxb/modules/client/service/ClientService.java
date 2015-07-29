package com.bxb.modules.client.service;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.StringUtil;
import org.springframework.stereotype.Service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.common.util.AgeUtil;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.ClientDao;
import com.bxb.modules.client.model.Address;
import com.bxb.modules.client.model.Client;
import com.bxb.modules.client.model.Email;
import com.bxb.modules.client.model.Phone;
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

	@Resource(name = "addressService")
	private IAddressService addressService;

	@Resource(name = "phoneService")
	private IPhoneService phoneService;

	@Resource(name = "emailService")
	private IEmailService emailService;

	private static final Logger logger = LogManager
			.getLogger(ClientService.class);

	@Override
	public Client findOneByIdObject(String _id, boolean isRedisplay) {

		Client client = this.clientdao.findOneByIdObject(_id, Client.class);

		if (client != null && isRedisplay) {
			reDisplay(client);
		}

		return client;
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

		// 如果有重名，则默认视为该客户，抛弃，返回同名客户的主键_id
		String client_name = client.getClient_name().trim();
		String _id = this.getOidByClientName(client_name,
				client.getOwner_user_id());
		if (StringUtil.isNotEmpty(_id)) {
			return _id;
		}

		client.setClient_name(client_name);
		setClientInf(client);
		this.setCreateInfo(client);
		String client_id = this.clientdao.insertObj(client);

		// 地址信息
		List<Address> addresses = client.getAddress_info();
		if (addresses != null && !addresses.isEmpty()) {
			this.addressService.add(addresses, client_id);
		}

		// 电话信息
		List<Phone> phones = client.getPhone_info();
		if (phones != null && !phones.isEmpty()) {
			this.phoneService.add(phones, client_id);
		}

		// 邮件信息
		List<Email> emails = client.getEmail_info();
		if (emails != null && !emails.isEmpty()) {
			this.emailService.add(emails, client_id);
		}

		return client_id;
	}

	private void setClientInf(Client client) {

		client.setPinYin();

		String birth_date = client.getBirth_date();
		if (StringUtil.isNotEmpty(birth_date)) {
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

	@Override
	public String getOidByClientName(String clientName, String userId) {

		DBObject returnFields = new BasicDBObject();
		returnFields.put("_id", 1);

		DBObject queryCondition = new BasicDBObject();

		queryCondition.put("owner_user_id", userId);// 常量类型
		queryCondition.put("client_name", clientName);// 常量值
		queryCondition.put("useflg", "1");

		DBObject result = this.clientdao.findOneByConditionPart(queryCondition,
				returnFields);

		if (result != null && result.get("_id") != null) {
			return result.get("_id").toString();
		}

		return null;
	}

	/****
	 * 设置回显信息
	 * 
	 * @param client
	 */
	private void reDisplay(Client client) {

		// 教育类型
		String education_type = client.getEducation_type();
		if (StringUtil.isNotEmpty(education_type)) {
			String education_type_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.EDUCATION_TYPE.getCode(),
							education_type);

			client.setEducation_type_name(education_type_name);
		}

	}

}
