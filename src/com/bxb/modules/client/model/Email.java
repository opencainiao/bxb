package com.bxb.modules.client.model;

import com.bxb.modules.base.BaseModel;

/****
 * 邮箱类
 * 
 * @author NBQ
 *
 */
public class Email extends BaseModel {

	private String owner_id; // 所有者id（可能是客户，也可能是用户）

	private String type_value; // 电话类型值(枚举值)
	private String type_name; // 电话类型名称
	private String email; // 联系邮箱

	private String mainflg; // 是否主要联系邮箱 1-是，0-否

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMainflg() {
		return mainflg;
	}

	public void setMainflg(String mainflg) {
		this.mainflg = mainflg;
	}

}
