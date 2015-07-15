package com.bxb.modules.client.model;

import com.bxb.modules.base.BaseModel;

/****
 * 电话类
 * 
 * @author NBQ
 *
 */
public class Phone extends BaseModel {

	private String owner_user_id; // 所属用户id

	private String phone_type_id; // 电话类型的mongodbId
	private String phone_type_name; // 电话类型名称
	private String phone_number; // 联系电话

	private String mainflg; // 是否主要联系电话 1-是，0-否

	public String getPhone_type_id() {
		return phone_type_id;
	}

	public void setPhone_type_id(String phone_type_id) {
		this.phone_type_id = phone_type_id;
	}

	public String getPhone_type_name() {
		return phone_type_name;
	}

	public void setPhone_type_name(String phone_type_name) {
		this.phone_type_name = phone_type_name;
	}

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

	public String getOwner_user_id() {
		return owner_user_id;
	}

	public void setOwner_user_id(String owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

}
