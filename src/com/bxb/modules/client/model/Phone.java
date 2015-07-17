package com.bxb.modules.client.model;

import com.bxb.modules.base.BaseModel;

/****
 * 电话类
 * 
 * @author NBQ
 *
 */
public class Phone extends BaseModel {

	private String owner_id; // 所有者id（可能是客户，也可能是用户）

	private String type_value; // 电话类型值(枚举值)
	private String type_name; // 电话类型名称
	private String phone_number; // 联系电话

	private String mainflg; // 是否主要联系电话 1-是，0-否

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getMainflg() {
		return mainflg;
	}

	public void setMainflg(String mainflg) {
		this.mainflg = mainflg;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getType_value() {
		return type_value;
	}

	public void setType_value(String type_value) {
		this.type_value = type_value;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

}
