package com.bxb.modules.client.model.partinfo;

import java.util.ArrayList;
import java.util.List;

import com.bxb.modules.base.BaseModel;

/****
 * 家庭信息
 * 
 * @author NBQ
 *
 */
public class ClientFamilyInfo extends BaseModel {

	private String marital_status; // 婚姻状况
	private String wedding_date; // 结婚日期
	private int boy_num; // 男孩数
	private int girl_num; // 女孩数
	private int children_num; // 子女数

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getWedding_date() {
		return wedding_date;
	}

	public void setWedding_date(String wedding_date) {
		this.wedding_date = wedding_date;
	}

	public int getBoy_num() {
		return boy_num;
	}

	public void setBoy_num(int boy_num) {
		this.boy_num = boy_num;
	}

	public int getGirl_num() {
		return girl_num;
	}

	public void setGirl_num(int girl_num) {
		this.girl_num = girl_num;
	}

	public int getChildren_num() {
		return children_num;
	}

	public void setChildren_num(int children_num) {
		this.children_num = children_num;
	}

	public static List<String> getTitles() {
		List<String> titles = new ArrayList<String>();

		titles.add("marital_status_name");
		titles.add("wedding_date");
		titles.add("boy_num");
		titles.add("girl_num");
		titles.add("children_num");

		return titles;
	}

	public static List<String> getTitleNames() {
		List<String> titleNames = new ArrayList<String>();

		titleNames.add("婚姻状况");
		titleNames.add("结婚日期");
		titleNames.add("男孩数");
		titleNames.add("女孩数");
		titleNames.add("子女数");

		return titleNames;
	}
}
