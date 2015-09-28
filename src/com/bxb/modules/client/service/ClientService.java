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
import com.bxb.modules.client.model.Phone;
import com.bxb.modules.infrastructure.enums.SysConstTypeEnum;
import com.bxb.modules.infrastructure.service.ICityService;
import com.bxb.modules.infrastructure.service.ISysConstService;
import com.mongodb.BasicDBList;
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

	@Resource(name = "cityService")
	private ICityService cityService;

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

		queryCondition.put("delflg", "0");

		PageVO pagevo = this.clientdao.batchSearchPage(queryCondition, sort,
				returnFields);

		List<DBObject> data = pagevo.getRows();

		for (DBObject dbo : data) {

			// 处理电话信息
			BasicDBList phone_info = (BasicDBList) dbo.get("phone_info");
			if (phone_info != null && !phone_info.isEmpty()) {
				StringBuffer sbphone = new StringBuffer();
				for (Object phone : phone_info) {
					StringBuffer sb_this = new StringBuffer();
					sb_this.append(((DBObject) phone).get("type_name"))
							.append("-")
							.append(((DBObject) phone).get("phone_number"));

					sbphone.append(sb_this).append("&lt;br&gt;"); // 添加一个换行符,
																	// flexigrid会使用jquery的htmlDecode方法对内容进行解码
				}

				dbo.put("phone_info_name", sbphone.toString());
			}

			// 处理地址信息
			BasicDBList address_info = (BasicDBList) dbo.get("address_info");
			if (address_info != null && !address_info.isEmpty()) {
				StringBuffer sbAddress = new StringBuffer();
				for (Object address : address_info) {
					StringBuffer sb_this = new StringBuffer();

					Integer province = (Integer) ((DBObject) address)
							.get("province"); // 省
					String province_name = this.cityService
							.findNameById(province); // 省

					Integer city = (Integer) ((DBObject) address).get("city"); // 市
					String city_name = this.cityService.findNameById(city); // 市

					Integer district = (Integer) ((DBObject) address)
							.get("district"); // 区
					String district_name = this.cityService
							.findNameById(district); // 区

					String detail_address = (String) ((DBObject) address)
							.get("detail_address");

					sb_this.append("(")
							.append(((DBObject) address).get("type_name"))
							.append(")").append(province_name)
							.append(city_name).append(district_name)
							.append("&lt;br&gt;").append(detail_address);

					sbAddress.append(sb_this).append("&lt;br&gt;"); // 添加一个换行符,
																	// flexigrid会使用jquery的htmlDecode方法对内容进行解码
				}

				dbo.put("address_info_name", sbAddress.toString());
			}
		}

		return pagevo;

	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields) {

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
		// List<Email> emails = client.getEmail_info();
		// this.emailService.add(emails, client_id);

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

		// 设置电话类型名
		List<Phone> phone_info = client.getPhone_info();
		if (phone_info != null && !phone_info.isEmpty()) {
			for (Phone phone : phone_info) {
				String type = phone.getType_value();

				String type_name = sysConstService.findDispValByTypecodAndVal(
						SysConstTypeEnum.PHONE_TYPE.getCode(), type);
				phone.setType_name(type_name);
			}
		}

		// 设置地址类型名
		List<Address> address_info = client.getAddress_info();
		if (address_info != null && !address_info.isEmpty()) {
			for (Address address : address_info) {

				String type = address.getType_value();
				String type_name = sysConstService.findDispValByTypecodAndVal(
						SysConstTypeEnum.ADDRESS_TYPE.getCode(), type);
				address.setType_name(type_name);
			}
		}
	}

	@Override
	public DBObject updatePart(DBObject returnFields, Client client) {

		DBObject toUpdate = makeUpdate(client);
		return this.clientdao.updateOneById(client.get_id_m(), returnFields,
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

		DBObject result = this.clientdao.findOneByConditionPart(queryCondition,
				returnFields);

		if (result != null && result.get("_id") != null) {
			return result.get("_id").toString();
		}

		return null;
	}

	/****
	 * 设置地址信息
	 * 
	 * @param client
	 */
	private void setAddressString(Client client) {

		List<Address> address_info = client.getAddress_info();
		if (address_info != null && !address_info.isEmpty()) {
			for (Address address : address_info) {

				Integer province = address.getProvince(); // 省
				String province_name = this.cityService.findNameById(province); // 省

				Integer city = address.getCity(); // 市
				String city_name = this.cityService.findNameById(city); // 市

				Integer district = address.getDistrict(); // 区
				String district_name = this.cityService.findNameById(district); // 区

				address.setProvince_name(province_name);
				address.setCity_name(city_name);
				address.setDistrict_name(district_name);
			}
		}

		// 地址
		client.setAddressString();
	}

	/****
	 * 设置回显信息
	 * 
	 * @param client
	 */
	private void reDisplay(Client client) {
		// 地址
		setAddressString(client);

		// 电话
		client.setPhoneString();

		// 性别
		String sex = client.getSex();
		if (StringUtil.isEmpty(sex)) {
			client.setSex_name("男");
		} else {
			if (sex.equals("1")) {
				client.setSex_name("男");
			} else {
				client.setSex_name("女");
			}
		}

		// 地区类型
		String region_type = client.getRegion_type();
		if (StringUtil.isNotEmpty(region_type)) {
			String region_type_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.REGION_TYPE.getCode(), region_type);

			client.setRegion_type_name(region_type_name);
		}

		// 教育类型
		String education_type = client.getEducation_type();
		if (StringUtil.isNotEmpty(education_type)) {
			String education_type_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.EDUCATION_TYPE.getCode(),
							education_type);

			client.setEducation_type_name(education_type_name);
		}

		// 婚姻状况
		String marital_status = client.getMarital_status();
		if (StringUtil.isNotEmpty(marital_status)) {
			String marital_status_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.MARITAL_STATUS.getCode(),
							marital_status);

			client.setMarital_status_name(marital_status_name);
		}

		// 企业性质
		String company_nature = client.getCompany_nature();
		if (StringUtil.isNotEmpty(company_nature)) {
			String company_nature_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.COMPANY_NATURE.getCode(),
							company_nature);

			client.setCompany_nature_name(company_nature_name);
		}

		// 行业类型
		String trade_type = client.getTrade_type();
		if (StringUtil.isNotEmpty(trade_type)) {
			String trade_type_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.TRADE_TYPE.getCode(), trade_type);

			client.setTrade_type_name(trade_type_name);
		}

		// 职业类型
		String career_type = client.getCareer_type();
		if (StringUtil.isNotEmpty(career_type)) {
			String career_type_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.CAREER_TYPE.getCode(), career_type);

			client.setCareer_type_name(career_type_name);
		}

		// 职位
		String job_position = client.getJob_position();
		if (StringUtil.isNotEmpty(job_position)) {
			String job_position_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.JOB_POSITION.getCode(),
							job_position);

			client.setJob_position_name(job_position_name);
		}

		// 职级
		String job_level = client.getJob_level();
		if (StringUtil.isNotEmpty(job_level)) {
			String job_level_name = sysConstService.findDispValByTypecodAndVal(
					SysConstTypeEnum.JOB_LEVEL.getCode(), job_level);

			client.setJob_level_name(job_level_name);
		}

		// （个人）年收入分类
		String annual_income_personal_type = client
				.getAnnual_income_personal_type();
		if (StringUtil.isNotEmpty(annual_income_personal_type)) {
			String annual_income_personal_type_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.ANNUAL_INCOME_PERSONAL_TYPE
									.getCode(), annual_income_personal_type);

			client.setAnnual_income_personal_type_name(annual_income_personal_type_name);
		}

		// （家庭）年收入分类
		String annual_income_family_type = client
				.getAnnual_income_family_type();
		if (StringUtil.isNotEmpty(annual_income_family_type)) {
			String annual_income_family_type_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.ANNUAL_INCOME_FAMILY_TYPE
									.getCode(), annual_income_family_type);

			client.setAnnual_income_family_type_name(annual_income_family_type_name);
		}

		// 家庭收入特点
		String family_income_feature = client.getFamily_income_feature();
		if (StringUtil.isNotEmpty(family_income_feature)) {
			String family_income_feature_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.FAMILY_INCOME_FEATURE.getCode(),
							family_income_feature);

			client.setFamily_income_feature_name(family_income_feature_name);
		}

		// 财务状况
		String family_financial_standing = client
				.getFamily_financial_standing();
		if (StringUtil.isNotEmpty(family_financial_standing)) {
			String family_financial_standing_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.FAMILY_FINANCIAL_STANDING
									.getCode(), family_financial_standing);

			client.setFamily_financial_standing_name(family_financial_standing_name);
		}

		// 客户来源
		String source_type = client.getSource_type();
		if (StringUtil.isNotEmpty(source_type)) {
			String source_type_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.SOURCE_TYPE.getCode(), source_type);

			client.setSource_type_name(source_type_name);
		}

		// 与介绍人关系
		String introducer_relationship = client.getIntroducer_relationship();
		if (StringUtil.isNotEmpty(introducer_relationship)) {
			String introducer_relationship_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.INTRODUCER_RELATIONSHIP.getCode(),
							introducer_relationship);

			client.setIntroducer_relationship_name(introducer_relationship_name);
		}

		// 与介绍人亲密度
		String introducer_closeness = client.getIntroducer_relationship();
		if (StringUtil.isNotEmpty(introducer_closeness)) {
			String introducer_closeness_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.INTRODUCER_CLOSENESS.getCode(),
							introducer_closeness);

			client.setIntroducer_closeness_name(introducer_closeness_name);
		}

		// 可接触度
		String contact_type = client.getContact_type();
		if (StringUtil.isNotEmpty(contact_type)) {
			String contact_type_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.CONTACT_TYPE.getCode(),
							contact_type);

			client.setContact_type_name(contact_type_name);
		}

		// 出生年代
		String birth_ages = client.getBirth_ages();
		if (StringUtil.isNotEmpty(birth_ages)) {
			String birth_ages_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.BIRTH_AGES.getCode(), birth_ages);

			client.setBirth_ages_name(birth_ages_name);
		}

		// 年龄段
		String age_group = client.getAge_group();
		if (StringUtil.isNotEmpty(age_group)) {
			String age_group_name = sysConstService.findDispValByTypecodAndVal(
					SysConstTypeEnum.AGE_GROUP.getCode(), age_group);

			client.setAge_group_name(age_group_name);
		}

		// 星座
		String constellation = client.getConstellation();
		if (StringUtil.isNotEmpty(constellation)) {
			String constellation_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.CONSTELLATION.getCode(),
							constellation);

			client.setConstellation_name(constellation_name);
		}

		// 血型
		String blood_group = client.getBlood_group();
		if (StringUtil.isNotEmpty(blood_group)) {
			String blood_group_name = sysConstService
					.findDispValByTypecodAndVal(
							SysConstTypeEnum.BLOOD_GROUP.getCode(), blood_group);

			client.setBlood_group_name(blood_group_name);
		}

		// 性格特点
		List<String> temperament_type = client.getTemperament_type();
		if (temperament_type != null && temperament_type.size() > 0) {

			StringBuilder sb = new StringBuilder();

			for (String temperament_t : temperament_type) {
				if (StringUtil.isNotEmpty(temperament_t)) {

					String temperament_type_name = sysConstService
							.findDispValByTypecodAndVal(
									SysConstTypeEnum.TEMPERAMENT_TYPE.getCode(),
									temperament_t);

					sb.append(temperament_type_name).append(" ");
				}
			}

			client.setTemperament_type_name(sb.toString());
		}

		// PDP类型
		String pdp_type = client.getPdp_type();
		if (StringUtil.isNotEmpty(pdp_type)) {
			String pdp_type_name = sysConstService.findDispValByTypecodAndVal(
					SysConstTypeEnum.PDP_TYPE.getCode(), pdp_type);

			client.setPdp_type_name(pdp_type_name);
		}

		// 关注的服务
		List<String> interesting_service = client.getInteresting_service();
		if (interesting_service != null && interesting_service.size() > 0) {

			StringBuilder sb = new StringBuilder();

			for (String interesting_service_t : interesting_service) {
				if (StringUtil.isNotEmpty(interesting_service_t)) {

					String interesting_service_name = sysConstService
							.findDispValByTypecodAndVal(
									SysConstTypeEnum.INTERESTING_SERVICE
											.getCode(), interesting_service_t);

					sb.append(interesting_service_name).append(" ");
				}
			}

			client.setInteresting_service_name(sb.toString());
		}
	}

	@Override
	public List<DBObject> findAllClientsByUserId(String userId,
			String last_op_time) {

		DBObject query = new BasicDBObject();
		query.put("owner_user_id", userId);
		query.put("delflg", "0");

		if (StringUtil.isNotEmpty(last_op_time)) {
			last_op_time = last_op_time.trim();
			if (last_op_time.length() >= 10) {
				query.put("last_op_date", last_op_time.substring(0, 10));
				query.put("last_op_time", new BasicDBObject("$gte",
						last_op_time));
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

		Client client = this.clientdao.findOneByConditionObject(query,
				Client.class);

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

	@Override
	public DBObject updateFull(Client client) {

		String valResult = validatForUpdate(client);

		if (StringUtil.isNotEmpty(valResult)) {
			throw new IllegalArgumentException(valResult);
		}

		DBObject toUpdate = makeUpdateFull(client);
		this.setModifyInfo(toUpdate);

		DBObject returnFields = new BasicDBObject();
		// returnFields.put("_id", 0);
		returnFields.put("state", 0);
		returnFields.put("last_op_user_id", 0);
		returnFields.put("last_op_user_name", 0);
		returnFields.put("last_op_date", 0);

		return this.clientdao.updateOneById(client.get_id_m(), returnFields,
				toUpdate);
	}

	private String validatForUpdate(Client client) {
		return "";
	}

	/****
	 * 组织要更新的字段
	 * 
	 * @param client
	 * @return
	 */
	private DBObject makeUpdateFull(Client client) {

		DBObject update = new BasicDBObject();
		DBObject updateSet = new BasicDBObject();

		updateSet.put("owner_user_id", client.getOwner_user_id());
		updateSet.put("client_name", client.getClient_name());
		updateSet.put("sex", client.getSex());
		updateSet.put("id_number", client.getId_number());
		updateSet.put("birth_date", client.getBirth_date());
		updateSet.put("address_info", client.getAddress_info());
		updateSet.put("phone_info", client.getPhone_info());
		updateSet.put("email_info", client.getEmail_info());
		updateSet.put("region_code", client.getRegion_code());
		updateSet.put("region_name", client.getRegion_name());
		updateSet.put("age", client.getAge());
		updateSet.put("region_type", client.getRegion_type());
		updateSet.put("education_type", client.getEducation_type());
		updateSet.put("company", client.getCompany());
		updateSet.put("company_nature", client.getCompany_nature());
		updateSet.put("trade_type", client.getTrade_type());
		updateSet.put("career_type", client.getCareer_type());
		updateSet.put("job_position", client.getJob_position());
		updateSet.put("job_level", client.getJob_level());
		updateSet.put("marital_status", client.getMarital_status_name());
		updateSet.put("wedding_date", client.getWedding_date());
		updateSet.put("boy_num", client.getBoy_num());
		updateSet.put("girl_num", client.getGirl_num());
		updateSet.put("children_num", client.getChildren_num());
		updateSet.put("annual_income_personal",
				client.getAnnual_income_personal());
		updateSet.put("annual_income_personal_type",
				client.getAnnual_income_personal_type());
		updateSet.put("annual_income_family", client.getAnnual_income_family());
		updateSet.put("annual_income_family_type",
				client.getAnnual_income_family_type());
		updateSet.put("family_income_feature",
				client.getFamily_income_feature());
		updateSet.put("family_financial_standing",
				client.getFamily_financial_standing());
		updateSet.put("source_type", client.getSource_type());
		updateSet.put("introducer_name", client.getIntroducer_name());
		updateSet.put("introducer_relationship",
				client.getIntroducer_relationship());
		updateSet.put("introducer_closeness", client.getIntroducer_closeness());
		updateSet.put("introducer_evaluation",
				client.getIntroducer_evaluation());
		updateSet.put("contact_type", client.getContact_type());
		updateSet.put("contact_attention", client.getContact_attention());
		updateSet.put("birth_ages", client.getBirth_ages());
		updateSet.put("age_group", client.getAge_group());
		updateSet.put("constellation", client.getConstellation());
		updateSet.put("blood_group", client.getBlood_group());
		updateSet.put("temperament_type", client.getTemperament_type());
		updateSet.put("pdp_type", client.getPdp_type());
		updateSet.put("hobbies", client.getHobbies());
		updateSet.put("interesting_service", client.getInteresting_service());

		this.setModifyInfoWithUserId(updateSet, client.getOwner_user_id());
		update.put("$set", updateSet);

		return update;
	}

	@Override
	public List<Client> downLoadAllClientByUserId(String userId) {

		DBObject query = new BasicDBObject();
		query.put("owner_user_id", userId);
		query.put("useflg", "1");
		query.put("delflg", "0");

		DBObject sort = new BasicDBObject();
		sort.put("client_name_full_py", 1);

		List<Client> clients = this.clientdao.findBatchObject(query, Client.class, sort);

		for (Client client : clients) {

			reDisplay(client);
			
			logger.debug(client);
		}
		
		return clients;
	}
}
