package com.bxb.modules.contract.model;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.mou.common.StringUtil;

import com.bxb.common.util.PinyinUtil;
import com.bxb.modules.base.BaseModel;

/****
 * 合同
 * 
 * @author NBQ
 *
 */
public class Contract extends BaseModel {

	private String owner_user_id; // 归属用户id

	private String policyholder;// 投保人
	private String policyholder_id_number; // 投保人身份证号
	private String insured_name; // 被保险人
	private String source_type; // 客户来源码
	private int sno; // 合同序号
	private String effective_date;// 保险合同生效日
	private String contract_no; // 保险合同编号
	private String insured_id_number;// 被保险人 身份证号
	private String insured_birth_date;// 被保险人 出生日期
	private String sex; // 性别
	private String policy_age; // 投保年龄

	private String pay_type; // 缴法
	private String pay_way; // 缴费方式
	private String bank_type; // 银行
	private String bank_no; // 银行卡号
	private String death_beneficiary; // 身故受益人
	private String relationship; // 关系
	private String benefit_ratio; // 受益比例
	private String physical_examination_type; // 体检方式

	private List<Insurance> insurances; // 险种信息

	private String status; // 状态
	private String sex_name;
	private String source_type_name; // 客户来源码

	private String insured_pinyin_name;// 姓名拼音， 比如：ZHANGSAN
	private String insured_first_char_header;// 姓名拼音第一个首字母， 比如：Z
	private String insured_all_char_header;// 姓名拼音首字母， 比如：ZS

	@NotEmpty(message = "归属用户_id不能为空")
	public String getOwner_user_id() {
		return owner_user_id;
	}

	public String getSource_type_name() {
		return source_type_name;
	}

	public void setSource_type_name(String source_type_name) {
		this.source_type_name = source_type_name;
	}

	public void setOwner_user_id(String owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

	@NotEmpty(message = "姓别不能为空")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex_name() {
		return sex_name;
	}

	public void setSex_name(String sex_name) {
		this.sex_name = sex_name;
	}

	public String getPolicyholder() {
		return policyholder;
	}

	public void setPolicyholder(String policyholder) {
		this.policyholder = policyholder;
	}

	public String getPolicyholder_id_number() {
		return policyholder_id_number;
	}

	public void setPolicyholder_id_number(String policyholder_id_number) {
		this.policyholder_id_number = policyholder_id_number;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getInsured_name() {
		return insured_name;
	}

	public void setInsured_name(String insured_name) {
		this.insured_name = insured_name;
	}

	public String getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getInsured_id_number() {
		return insured_id_number;
	}

	public void setInsured_id_number(String insured_id_number) {
		this.insured_id_number = insured_id_number;
	}

	public String getInsured_birth_date() {
		return insured_birth_date;
	}

	public void setInsured_birth_date(String insured_birth_date) {
		this.insured_birth_date = insured_birth_date;
	}

	public String getPolicy_age() {
		return policy_age;
	}

	public void setPolicy_age(String policy_age) {
		this.policy_age = policy_age;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getPay_way() {
		return pay_way;
	}

	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getBank_no() {
		return bank_no;
	}

	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}

	public String getDeath_beneficiary() {
		return death_beneficiary;
	}

	public void setDeath_beneficiary(String death_beneficiary) {
		this.death_beneficiary = death_beneficiary;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getBenefit_ratio() {
		return benefit_ratio;
	}

	public void setBenefit_ratio(String benefit_ratio) {
		this.benefit_ratio = benefit_ratio;
	}

	public String getPhysical_examination_type() {
		return physical_examination_type;
	}

	public void setPhysical_examination_type(String physical_examination_type) {
		this.physical_examination_type = physical_examination_type;
	}

	public List<Insurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	public String getSource_type() {
		return source_type;
	}

	public void setSource_type(String source_type) {
		this.source_type = source_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPinyin_name() {
		return insured_pinyin_name;
	}

	public void setPinyin_name(String pinyin_name) {
		this.insured_pinyin_name = pinyin_name;
	}

	public String getFirst_char_header() {
		return insured_first_char_header;
	}

	public void setFirst_char_header(String first_char_header) {
		this.insured_first_char_header = first_char_header;
	}

	public String getAll_char_header() {
		return insured_all_char_header;
	}

	public void setAll_char_header(String all_char_header) {
		this.insured_all_char_header = all_char_header;
	}

	public void setPinYin() {

		if (StringUtil.isEmpty(this.insured_pinyin_name)) {
			// 关键字的全拼
			String nameFullPy = PinyinUtil.str2Pinyin(insured_name, null);
			setPinyin_name(nameFullPy);
		}

		if (StringUtil.isEmpty(this.insured_all_char_header)) {
			// 关键字的简拼
			String nameShortPy = PinyinUtil.strFirst2Pinyin(insured_name);
			setAll_char_header(nameShortPy);
		}

		if (StringUtil.isEmpty(this.insured_first_char_header)) {
			// 首字母
			if (StringUtil.isNotEmpty(this.insured_pinyin_name)) {
				setFirst_char_header(this.insured_pinyin_name.substring(0, 1));
			} else {
				String headerFirst = PinyinUtil
						.str2PinyinHeaderFirst(insured_name);
				setFirst_char_header(headerFirst);
			}
		}
	}

}
