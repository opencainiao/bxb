package com.bxb.modules.infrastructure.model;

import com.bxb.modules.base.BaseModel;

/****
 * 系统标签，标示标签的值信息
 * 
 * @author NBQ
 *
 */
public class TagType extends BaseModel {

	private String tag_type_code; // 标签类型码
	private String tag_type_name;// 标签类型名称
	
	public String getTag_type_code() {
		return tag_type_code;
	}

	public void setTag_type_code(String tag_type_code) {
		this.tag_type_code = tag_type_code;
	}

	public String getTag_type_name() {
		return tag_type_name;
	}

	public void setTag_type_name(String tag_type_name) {
		this.tag_type_name = tag_type_name;
	}
}
