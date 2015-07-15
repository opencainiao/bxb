package com.bxb.modules.client.model;

import com.bxb.modules.base.BaseModel;

/****
 * 地址
 * 
 * @author NBQ
 *
 */
public class Address extends BaseModel {

	private String owner_user_id; // 所属用户id

	private String address_type_id; // 地址类型的mongodbId
	private String address_type_value; // 地址类型值(枚举值)
	private String address_type_name; // 地址类型名称
	private String province; // 省
	private String city; // 市
	private String district; // 区
	private String detail_address; // 详细地址

	private String mainflg; // 是否主要地址 1-是，0-否

	public String getAddress_type_id() {
		return address_type_id;
	}

	public void setAddress_type_id(String address_type_id) {
		this.address_type_id = address_type_id;
	}

	public String getAddress_type_name() {
		return address_type_name;
	}

	public void setAddress_type_name(String address_type_name) {
		this.address_type_name = address_type_name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	public String getMainflg() {
		return mainflg;
	}

	public void setMainflg(String mainflg) {
		this.mainflg = mainflg;
	}

	public String getAddress_type_value() {
		return address_type_value;
	}

	public void setAddress_type_value(String address_type_value) {
		this.address_type_value = address_type_value;
	}

	public String getOwner_user_id() {
		return owner_user_id;
	}

	public void setOwner_user_id(String owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

}