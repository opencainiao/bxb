package com.bxb.modules.client.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.bxb.modules.base.BaseModel;

/****
 * 地址类型
 * 
 * @author NBQ
 *
 */
public class AddressType extends BaseModel {

	private String owner_user_id; // 所属用户id
	private String address_type_value; // 地址类型值(枚举值)
	private String address_type_name; // 地址类型名称
	private String address_type_character;// 地址类型性质 1-系统内置 2-用户自定义

	public String getOwner_user_id() {
		return owner_user_id;
	}

	public void setOwner_user_id(String owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

	@NotEmpty(message = "类型名不能为空")
	public String getAddress_type_name() {
		return address_type_name;
	}

	public void setAddress_type_name(String address_type_name) {
		this.address_type_name = address_type_name;
	}

	public String getAddress_type_character() {
		return address_type_character;
	}

	public void setAddress_type_character(String address_type_character) {
		this.address_type_character = address_type_character;
	}

	@NotEmpty(message = "类型值不能为空")
	public String getAddress_type_value() {
		return address_type_value;
	}

	public void setAddress_type_value(String address_type_value) {
		this.address_type_value = address_type_value;
	}

}
