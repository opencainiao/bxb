package com.bxb.modules.client.model;

import com.bxb.modules.base.BaseModel;

/****
 * 邮箱类型
 * 
 * @author NBQ
 *
 */
public class EmailType extends BaseModel {

	private String owner_user_id; // 所属用户id
	private String email_type_value; // 邮箱类型值(枚举值)
	private String email_type_name; // 邮箱类型名称
	private String email_type_character;// 邮箱类型性质 1-系统内置 2-用户自定义

	public String getOwner_user_id() {
		return owner_user_id;
	}

	public void setOwner_user_id(String owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

	public String getEmail_type_name() {
		return email_type_name;
	}

	public void setEmail_type_name(String email_type_name) {
		this.email_type_name = email_type_name;
	}

	public String getEmail_type_character() {
		return email_type_character;
	}

	public void setEmail_type_character(String email_type_character) {
		this.email_type_character = email_type_character;
	}

	public String getEmail_type_value() {
		return email_type_value;
	}

	public void setEmail_type_value(String email_type_value) {
		this.email_type_value = email_type_value;
	}

}
