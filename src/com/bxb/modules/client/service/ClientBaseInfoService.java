package com.bxb.modules.client.service;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.StringUtil;
import org.springframework.stereotype.Service;

import com.bxb.common.util.AgeUtil;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.ClientBaseInfoDao;
import com.bxb.modules.client.model.Address;
import com.bxb.modules.client.model.Client;
import com.bxb.modules.client.model.ClientBaseInfo;
import com.bxb.modules.client.model.Email;
import com.bxb.modules.client.model.Phone;
import com.bxb.modules.infrastructure.enums.SysConstTypeEnum;
import com.bxb.modules.infrastructure.service.ISysConstService;
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

	@Resource(name = "sysConstService")
	private ISysConstService sysConstService;

	@Resource(name = "addressService")
	private IAddressService addressService;

	@Resource(name = "phoneService")
	private IPhoneService phoneService;

	@Resource(name = "emailService")
	private IEmailService emailService;

	private static final Logger logger = LogManager
			.getLogger(ClientBaseInfoService.class);

	@Override
	public DBObject updatePart(DBObject returnFields,
			ClientBaseInfo clientbaseinfo) {

		DBObject toUpdate = makeUpdate(clientbaseinfo);
		DBObject updatedResult = this.clientbaseinfodao.updateOneById(
				clientbaseinfo.get_id_str(), returnFields, toUpdate);

		String client_id = clientbaseinfo.get_id_str();

		// 地址信息
		List<Address> addresses = clientbaseinfo.getAddress_info();
		if (addresses != null && !addresses.isEmpty()) {
			this.addressService.add(addresses, client_id);
		}

		// 电话信息
		List<Phone> phones = clientbaseinfo.getPhone_info();
		if (phones != null && !phones.isEmpty()) {
			this.phoneService.add(phones, client_id);
		}

		// 邮件信息
		List<Email> emails = clientbaseinfo.getEmail_info();
		if (emails != null && !emails.isEmpty()) {
			this.emailService.add(emails, client_id);
		}

		return updatedResult;
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
		setClientInf(clientbaseinfo);

		updateSet.put("region_code", clientbaseinfo.getRegion_code());
		updateSet.put("region_name", clientbaseinfo.getRegion_name());
		updateSet.put("region_type", clientbaseinfo.getRegion_type());

		String education_type = clientbaseinfo.getEducation_type();
		updateSet.put("education_type", education_type);
		if (StringUtil.isNotEmpty(education_type)) {
			String education_type_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.EDUCATION_TYPE.getCode(),
							clientbaseinfo.getEducation_type());
			updateSet.put("education_type_name", education_type_name);
		}

		updateSet.put("address_info", clientbaseinfo.getAddress_info());
		updateSet.put("phone_info", clientbaseinfo.getPhone_info());
		updateSet.put("email_info", clientbaseinfo.getEmail_info());
		
		this.setModifyInfo(updateSet);
		update.put("$set", updateSet);

		logger.debug("更新的对象信息\n{}", update);
		return update;
	}

	private void setClientInf(ClientBaseInfo clientbaseinfo) {

		clientbaseinfo.setPinYin();

		String birth_date = clientbaseinfo.getBirth_date();
		if (StringUtil.isNotEmpty(birth_date)) {
			try {
				clientbaseinfo.setAge(AgeUtil.getAge(birth_date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ClientBaseInfo findOneByIdObject(String _id) {

		return this.clientbaseinfodao.findOneByIdObject(_id,
				ClientBaseInfo.class);
	}

	@Override
	public DBObject updatePart(DBObject returnFields, Client client) {

		return this.updatePart(returnFields, client.getBaseInf());
	}
}
