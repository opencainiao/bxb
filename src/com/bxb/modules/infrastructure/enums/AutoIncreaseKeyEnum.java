package com.bxb.modules.infrastructure.enums;

public enum AutoIncreaseKeyEnum {

	MENU_CODE("MENU_CODE", "系统菜单");

	private String code;
	private String name;

	private AutoIncreaseKeyEnum(String code, String name) {
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
