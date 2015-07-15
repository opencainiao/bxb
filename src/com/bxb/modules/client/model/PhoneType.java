package com.bxb.modules.client.model;

import com.bxb.modules.base.BaseModel;

/****
 * 手机类型
 * 
 * @author NBQ
 *
 */
public class PhoneType extends BaseModel {

	private String owner_user_id; // 所属用户id
	private String phone_type_value; // 电话类型值(枚举值)
	private String phone_type_name; // 电话类型名称
	private String phone_type_character;// 电话类型性质 1-系统内置 2-用户自定义

	public String getOwner_user_id() {
		return owner_user_id;
	}

	public void setOwner_user_id(String owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

	public String getPhone_type_name() {
		return phone_type_name;
	}

	public void setPhone_type_name(String phone_type_name) {
		this.phone_type_name = phone_type_name;
	}

	public String getPhone_type_character() {
		return phone_type_character;
	}

	public void setPhone_type_character(String phone_type_character) {
		this.phone_type_character = phone_type_character;
	}

	public String getPhone_type_value() {
		return phone_type_value;
	}

	public void setPhone_type_value(String phone_type_value) {
		this.phone_type_value = phone_type_value;
	}

}
