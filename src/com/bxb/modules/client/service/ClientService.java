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

	private static final Logger logger = LogManager.getLogger(ClientService.class);

	@Override
	public Client findOneByIdObject(String _id, boolean isRedisplay) {

		Client client = this.clientdao.findOneByIdObject(_id, Client.class);

		if (client != null && isRedisplay) {
			reDisplay(client);
		}

		return client;
	}

	@Override
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort, DBObject returnFields) {

		queryCondition.put("delflg", "0");

		return this.clientdao.batchSearchPage(queryCondition, sort, returnFields);
	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort, DBObject returnFields) {

		query.put("delflg", "0");

		return this.clientdao.batchSearchOnePage(query, sort, returnFields);
	}

	@Override
	public String add(Client client) {

		// 如果有重名，则默认视为该客户，抛弃，返回同名客户的主键_id
		String client_name = client.getClient_name().trim();
		// String _id = this.getOidByClientName(client_name,
		// client.getOwner_user_id());
		// if (StringUtil.isNotEmpty(_id)) {
		// return _id;
		// }

		client.setClient_name(client_name);
		setClientInf(client);
		this.setCreateInfoWithUserId(client, client.getOwner_user_id());
		String client_id = this.clientdao.insertObj(client);

		// 地址信息
		List<Address> addresses = client.getAddress_info();
		this.addressService.add(addresses, client_id);

		// 电话信息
		List<Phone> phones = client.getPhone_info();
		this.phoneService.add(phones, client_id);

		// 邮件信息
		List<Email> emails = client.getEmail_info();
		this.emailService.add(emails, client_id);

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
		return this.clientdao.updateOneById(client.get_id_m(), returnFields, toUpdate);
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

		this.setModifyInfoWithUserId(updateSet, client.getOwner_user_id());
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
		queryCondition.put("delflg", "0");

		DBObject result = this.clientdao.findOneByConditionPart(queryCondition, returnFields);

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
					.findDispValByTypecodAndVal(SysConstTypeEnum.EDUCATION_TYPE.getCode(), education_type);

			client.setEducation_type_name(education_type_name);
		}

		// 婚姻状况
		String marital_status = client.getMarital_status();
		if (StringUtil.isNotEmpty(marital_status)) {
			String marital_status_name = sysConstService
					.findDispValByTypecodAndVal(SysConstTypeEnum.MARITAL_STATUS.getCode(), marital_status);

			client.setMarital_status_name(marital_status_name);
		}

		// 企业性质
		String company_nature = client.getCompany_nature();
		if (StringUtil.isNotEmpty(company_nature)) {
			String company_nature_name = sysConstService
					.findDispValByTypecodAndVal(SysConstTypeEnum.COMPANY_NATURE.getCode(), company_nature);

			client.setCompany_nature_name(company_nature_name);
		}

		// 行业类型
		String trade_type = client.getTrade_type();
		if (StringUtil.isNotEmpty(trade_type)) {
			String trade_type_name = sysConstService.findDispValByTypecodAndVal(SysConstTypeEnum.TRADE_TYPE.getCode(),
					trade_type);

			client.setTrade_type_name(trade_type_name);
		}

		// 职业类型
		String career_type = client.getCareer_type();
		if (StringUtil.isNotEmpty(career_type)) {
			String career_type_name = sysConstService.findDispValByTypecodAndVal(SysConstTypeEnum.CAREER_TYPE.getCode(),
					career_type);

			client.setCareer_type_name(career_type_name);
		}

		// 职位
		String job_position = client.getJob_position();
		if (StringUtil.isNotEmpty(job_position)) {
			String job_position_name = sysConstService
					.findDispValByTypecodAndVal(SysConstTypeEnum.JOB_POSITION.getCode(), job_position);

			client.setJob_position_name(job_position_name);
		}

		// 职级
		String job_level = client.getJob_level();
		if (StringUtil.isNotEmpty(job_level)) {
			String job_level_name = sysConstService.findDispValByTypecodAndVal(SysConstTypeEnum.JOB_LEVEL.getCode(),
					job_level);

			client.setJob_level_name(job_level_name);
		}

		// （个人）年收入分类
		String annual_income_personal_type = client.getAnnual_income_personal_type();
		if (StringUtil.isNotEmpty(annual_income_personal_type)) {
			String annual_income_personal_type_name = sysConstService.findDispValByTypecodAndVal(
					SysConstTypeEnum.ANNUAL_INCOME_PERSONAL_TYPE.getCode(), annual_income_personal_type);

			client.setAnnual_income_personal_type_name(annual_income_personal_type_name);
		}

		// （家庭）年收入分类
		String annual_income_family_type = client.getAnnual_income_family_type();
		if (StringUtil.isNotEmpty(annual_income_family_type)) {
			String annual_income_family_type_name = sysConstService.findDispValByTypecodAndVal(
					SysConstTypeEnum.ANNUAL_INCOME_FAMILY_TYPE.getCode(), annual_income_family_type);

			client.setAnnual_income_family_type_name(annual_income_family_type_name);
		}

		// 家庭收入特点
		String family_income_feature = client.getFamily_income_feature();
		if (StringUtil.isNotEmpty(family_income_feature)) {
			String family_income_feature_name = sysConstService.findDispValByTypecodAndVal(
					SysConstTypeEnum.FAMILY_INCOME_FEATURE.getCode(), family_income_feature);

			client.setFamily_income_feature_name(family_income_feature_name);
		}

		// 财务状况
		String family_financial_standing = client.getFamily_financial_standing();
		if (StringUtil.isNotEmpty(family_financial_standing)) {
			String family_financial_standing_name = sysConstService.findDispValByTypecodAndVal(
					SysConstTypeEnum.FAMILY_FINANCIAL_STANDING.getCode(), family_financial_standing);

			client.setFamily_financial_standing_name(family_financial_standing_name);
		}

		// 客户来源
		String source_type = client.getSource_type();
		if (StringUtil.isNotEmpty(source_type)) {
			String source_type_name = sysConstService.findDispValByTypecodAndVal(SysConstTypeEnum.SOURCE_TYPE.getCode(),
					source_type);

			client.setSource_type_name(source_type_name);
		}

		// 与介绍人关系
		String introducer_relationship = client.getIntroducer_relationship();
		if (StringUtil.isNotEmpty(introducer_relationship)) {
			String introducer_relationship_name = sysConstService.findDispValByTypecodAndVal(
					SysConstTypeEnum.INTRODUCER_RELATIONSHIP.getCode(), introducer_relationship);

			client.setIntroducer_relationship_name(introducer_relationship_name);
		}

		// 与介绍人亲密度
		String introducer_closeness = client.getIntroducer_relationship();
		if (StringUtil.isNotEmpty(introducer_closeness)) {
			String introducer_closeness_name = sysConstService
					.findDispValByTypecodAndVal(SysConstTypeEnum.INTRODUCER_CLOSENESS.getCode(), introducer_closeness);

			client.setIntroducer_closeness_name(introducer_closeness_name);
		}
	}

	@Override
	public List<DBObject> findAllClientsByUserId(String userId, String last_op_time) {

		DBObject query = new BasicDBObject();
		query.put("owner_user_id", userId);
		query.put("delflg", "0");

		if (StringUtil.isNotEmpty(last_op_time)) {
			last_op_time = last_op_time.trim();
			if (last_op_time.length() >= 10) {
				query.put("last_op_date", last_op_time.substring(0, 10));
				query.put("last_op_time", new BasicDBObject("$gte", last_op_time));
			}
		}

		DBObject sort = new BasicDBObject();
		sort.put("client_name_full_py", 1);
		DBObject returnFields = null;

		return this.clientdao.findBatchDbOjbect(query, sort, returnFields);
	}

	@Override
	public Client findOneByCondition(DBObject query, boolean isRedisplay) {

		query.put("delflg", "0");

		Client client = this.clientdao.findOneByConditionObject(query, Client.class);

		if (client != null && isRedisplay) {
			this.reDisplay(client);
		}

		return client;
	}

	@Override
	public DBObject findOneByConditionDBObj(DBObject query) {

		query.put("delflg", "0");

		return this.clientdao.findOneByConditionPart(query, null);
	}

	public static void main(String[] args) {
		System.out.println("2015-08-121".substring(0, 10));
	}
}
