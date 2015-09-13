package com.bxb.modules.infrastructure.enums;

public enum SysConstTypeEnum {

	SYS_MODULE("SYS_MODULE", " 模块"), //
	EDUCATION_TYPE("EDUCATION_TYPE", "教育类型"), //
	MARITAL_STATUS("MARITAL_STATUS", "婚姻情况"), //
	COMPANY_NATURE("COMPANY_NATURE", "企业性质"), //
	TRADE_TYPE("TRADE_TYPE", "行业类型"), //
	CAREER_TYPE("CAREER_TYPE", "职业类型"), //
	JOB_POSITION("JOB_POSITION", "职位"), //
	JOB_LEVEL("JOB_LEVEL", "职级"), //
	ANNUAL_INCOME_PERSONAL_TYPE("ANNUAL_INCOME_PERSONAL_TYPE", "年收入分类（个人）"), //
	ANNUAL_INCOME_FAMILY_TYPE("ANNUAL_INCOME_FAMILY_TYPE", "年收入分类（家庭）"), //
	FAMILY_INCOME_FEATURE("FAMILY_INCOME_FEATURE", "家庭年收入特点"), //
	FAMILY_FINANCIAL_STANDING("FAMILY_FINANCIAL_STANDING", "财务状况"), //
	SOURCE_TYPE("SOURCE_TYPE", "客户来源"), //
	INTRODUCER_RELATIONSHIP("INTRODUCER_RELATIONSHIP", "与介绍人关系"), //
	INTRODUCER_CLOSENESS("INTRODUCER_CLOSENESS", "与介绍人亲密度"), //
	CONTACT_TYPE("CONTACT_TYPE", "可接触度"), //
	BIRTH_AGES("BIRTH_AGES", "出生年代"), //
	AGE_GROUP("AGE_GROUP", "年龄段"), //
	CONSTELLATION("CONSTELLATION", "星座"), //
	BLOOD_GROUP("BLOOD_GROUP", "血型"), //
	TEMPERAMENT_TYPE("TEMPERAMENT_TYPE", "性格特点"), //
	PDP_TYPE("PDP_TYPE", "PDP类型"), //
	INTERESTING_SERVICE("INTERESTING_SERVICE", "关注的服务"),
	REGION_TYPE("REGION_TYPE","地区分类"),
	PHONE_TYPE("PHONE_TYPE","电话类型"),
	ADDRESS_TYPE("ADDRESS_TYPE","地址类型");
	

	private String code;
	private String name;

	private SysConstTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
