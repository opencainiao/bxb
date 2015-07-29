package com.bxb.modules.infrastructure.enums;

import com.bxb.modules.user.enumes.UserCharacter;

public enum SysConstTypeEnum {
	EDUCATION_TYPE("EDUCATION_TYPE", "教育类型");//

	private String code;
	private String name;

	public static UserCharacter getUserCharacterByCode(String code) {

		UserCharacter rtnUserCharacter = null;

		if (code.equals(UserCharacter.BACKEND_MANAGER_USER.getCode())) {
			rtnUserCharacter = UserCharacter.BACKEND_MANAGER_USER;
		} else if (code.equals(UserCharacter.NORMAL_REGIST_USER.getCode())) {
			rtnUserCharacter = UserCharacter.NORMAL_REGIST_USER;
		} else {
			rtnUserCharacter = UserCharacter.INVALID_USER;
		}

		return rtnUserCharacter;
	}

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
