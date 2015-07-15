package com.bxb.modules.client.model;

import com.bxb.modules.base.BaseModel;

/****
 * 邮箱类
 * 
 * @author NBQ
 *
 */
public class Email extends BaseModel {

	private String owner_user_id; // 所属用户id

	private String email_type_id; // 邮箱类型的mongodbId
	private String email_type_name; // 邮箱类型名称
	private String email; // 联系邮箱

	public String getEmail_type_id() {
		return email_type_id;
	}

	public void setEmail_type_id(String email_type_id) {
		this.email_type_id = email_type_id;
	}

	public String getEmail_type_name() {
		return email_type_name;
	}

	public void setEmail_type_name(String email_type_name) {
		this.email_type_name = email_type_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOwner_user_id() {
		return owner_user_id;
	}

	public void setOwner_user_id(String owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

}
