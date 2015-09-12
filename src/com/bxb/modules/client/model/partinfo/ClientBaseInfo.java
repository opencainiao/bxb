package com.bxb.modules.client.model.partinfo;

import java.util.ArrayList;
import java.util.List;

import com.bxb.common.util.PinyinUtil;
import com.bxb.modules.base.BaseModel;
import com.bxb.modules.client.model.Address;
import com.bxb.modules.client.model.Email;
import com.bxb.modules.client.model.Phone;

public class ClientBaseInfo extends BaseModel {

	private String owner_user_id; // 归属用户id
	private String client_name; // 客户姓名
	private String sex; // 性别
	private String id_number; // 身份证号
	private String birth_date; // 生日
	private int age; // 年龄
	private List<Address> address_info; // 客户的地址信息
	private List<Phone> phone_info; // 客户的电话信息
	// private List<Email> email_info; // 客户的邮箱信息
	private String email_info;
	private String region_code; // 地区码
	private String region_name; // 地区名
	private String region_type; // 地区分类
	private String education_type; // 教育程度
	private String education_type_name; // 教育程度
	private String name_card_id; // 名片id

	private String client_name_full_py; // 名称全拼
	private String client_name_short_py; // 名称首字母拼音

	public void setOwner_user_id(String owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

	public String getOwner_user_id() {
		return owner_user_id;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setEmail_info(String email_info) {
		this.email_info = email_info;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getId_number() {
		return id_number;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}

	public String getRegion_code() {
		return region_code;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_type(String region_type) {
		this.region_type = region_type;
	}

	public String getRegion_type() {
		return region_type;
	}

	public void setEducation_type(String education_type) {
		this.education_type = education_type;
	}

	public String getEducation_type() {
		return education_type;
	}

	public void setName_card_id(String name_card_id) {
		this.name_card_id = name_card_id;
	}

	public String getName_card_id() {
		return name_card_id;
	}

	public String getEducation_type_name() {
		return education_type_name;
	}

	public void setEducation_type_name(String education_type_name) {
		this.education_type_name = education_type_name;
	}

	public List<Address> getAddress_info() {
		return address_info;
	}

	public void setAddress_info(List<Address> address_info) {
		this.address_info = address_info;
	}

	public List<Phone> getPhone_info() {
		return phone_info;
	}

	public void setPhone_info(List<Phone> phone_info) {
		this.phone_info = phone_info;
	}

//	public List<Email> getEmail_info() {
//		return email_info;
//	}

	// public void setEmail_info(List<Email> email_info) {
	// this.email_info = email_info;
	// }

	public void setPinYin() {
		// 关键字的全拼
		String nameFullPy = PinyinUtil.str2Pinyin(client_name, null);
		setClient_name_full_py(nameFullPy);

		// 关键字的简拼
		String nameShortPy = PinyinUtil.strFirst2Pinyin(client_name);
		setClient_name_short_py(nameShortPy);
	}

	public String getClient_name_full_py() {
		return client_name_full_py;
	}

	public void setClient_name_full_py(String client_name_full_py) {
		this.client_name_full_py = client_name_full_py;
	}

	public String getClient_name_short_py() {
		return client_name_short_py;
	}

	public void setClient_name_short_py(String client_name_short_py) {
		this.client_name_short_py = client_name_short_py;
	}

	public static List<String> getTitles() {
		List<String> titles = new ArrayList<String>();

		titles.add("owner_user_id");
		titles.add("client_name");
		titles.add("sex");
		titles.add("id_number");
		titles.add("birth_date");
		titles.add("age");
		titles.add("email_info");
		titles.add("phone_info");
		titles.add("address_info");
		titles.add("region_code");
		titles.add("region_name");
		titles.add("region_type");
		// titles.add("education_type");
		titles.add("education_type_name");
		titles.add("name_card_id");

		return titles;
	}

	public static List<String> getTitleNames() {
		List<String> titleNames = new ArrayList<String>();

		titleNames.add("归属用户id");
		titleNames.add("姓名");
		titleNames.add("性别");
		titleNames.add("身份证");
		titleNames.add("生日");
		titleNames.add("年龄");
		titleNames.add("邮箱");
		titleNames.add("电话");
		titleNames.add("地址");
		titleNames.add("地区码");
		titleNames.add("地区名");
		titleNames.add("地区分类");
		titleNames.add("教育程度");
		titleNames.add("名片id");

		return titleNames;
	}
}
