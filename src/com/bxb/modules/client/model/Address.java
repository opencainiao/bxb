package com.bxb.modules.client.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.bxb.modules.base.BaseModel;

/****
 * 地址
 * 
 * @author NBQ
 *
 */
public class Address extends BaseModel {

	private String owner_id; // 所有者id（可能是客户，也可能是用户）

	private String type_value; // 地址类型值(枚举值)
	private String type_name; // 地址类型名称
	private Integer province; // 省
	private String province_name; // 省
	private Integer city; // 市
	private String city_name; // 市
	private Integer district; // 区
	private String district_name; // 区
	private String detail_address; // 详细地址

	private String mainflg; // 是否主要地址 1-是，0-否

	@NotEmpty(message = "省代码不能为空")
	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@NotEmpty(message = "市代码不能为空")
	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	@NotEmpty(message = "详细地址不能为空")
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

	@NotEmpty(message = "所有者不能为空")
	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
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

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public Object getString() {

		StringBuffer sb = new StringBuffer();
		sb.append(type_name).append("<br>").append(province_name).append(" ").append(city_name).append(" ").append(district_name).append("<br>").append(detail_address);

		return sb.toString();
	}

}