package com.bxb.modules.client.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.bxb.modules.base.BaseModel;

/****
 * 通用类型
 * 
 * @author NBQ
 *
 */
public class CommonType extends BaseModel {

	private String owner_user_id; // 所属用户id
	private String type_value; // 类型值(枚举值)
	private String type_name; // 类型名称
	private String type_character;// 类型性质 1-系统内置 2-用户自定义

	public String getOwner_user_id() {
		return owner_user_id;
	}

	public void setOwner_user_id(String owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

	@NotEmpty(message = "类型值不能为空")
	public String getType_value() {
		return type_value;
	}

	public void setType_value(String type_value) {
		this.type_value = type_value;
	}

	@NotEmpty(message = "类型名不能为空")
	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getType_character() {
		return type_character;
	}

	public void setType_character(String type_character) {
		this.type_character = type_character;
	}
}
