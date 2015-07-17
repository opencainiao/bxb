package com.bxb.modules.infrastructure.model;

import com.bxb.modules.base.BaseModel;

/****
 * 通用类型
 * 
 * @author NBQ
 *
 */
public class City extends BaseModel {

	private int id; // id
	private String code; // 编码
	private String name; // 名称
	private int level; // 层级
	private int parent_id; // 父id
	private int province; // 省市代码

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}
}
