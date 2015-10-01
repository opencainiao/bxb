package com.bxb.modules.infrastructure.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.bxb.modules.base.BaseModel;

/****
 * 客户间关系
 * 
 * @author NBQ
 *
 */
public class ClientRelationShip extends BaseModel {

	private String f_id; // 第一个人的id
	private String f_name; // 第一个人的姓名
	private String f_sex; // 第一个人的性别
	private String s_id;// 第二个人的id
	private String s_name; // 第二个人的姓名
	private String s_sex; // 第二个人的性别
	private String relationship;// 关系
	private String relationship_cmt;// 关系说明

	@NotEmpty(message = "id不能为空")
	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	@NotEmpty(message = "姓名不能为空")
	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_sex() {
		return f_sex;
	}

	public void setF_sex(String f_sex) {
		this.f_sex = f_sex;
	}

	@NotEmpty(message = "亲属id不能为空")
	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	@NotEmpty(message = "亲属姓名不能为空")
	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_sex() {
		return s_sex;
	}

	public void setS_sex(String s_sex) {
		this.s_sex = s_sex;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getRelationship_cmt() {
		return relationship_cmt;
	}

	public void setRelationship_cmt(String relationship_cmt) {
		this.relationship_cmt = relationship_cmt;
	}

}
