package com.bxb.modules.infrastructure.enums;


public enum SysConstTypeEnum {
	EDUCATION_TYPE("EDUCATION_TYPE", "教育类型"), //
	MARITAL_STATUS("MARITAL_STATUS", "婚姻情况");//

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
