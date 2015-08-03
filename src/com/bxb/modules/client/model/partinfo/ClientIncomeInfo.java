package com.bxb.modules.client.model.partinfo;

import java.util.ArrayList;
import java.util.List;

import com.bxb.modules.base.BaseModel;

public class ClientIncomeInfo extends BaseModel {
	
	public static List<String> getTitles() {
		List<String> titles = new ArrayList<String>();

		titles.add("annual_income_personal");
		titles.add("annual_income_personal_type_name");
		titles.add("annual_income_family");
		titles.add("annual_income_family_type_name");
		titles.add("family_income_feature_name");
		titles.add("family_financial_standing_name");

		return titles;
	}

	public static List<String> getTitleNames() {
		List<String> titleNames = new ArrayList<String>();

		titleNames.add("年收入（个人）");
		titleNames.add("年收入分类（个人）");
		titleNames.add("年收入（家庭）");
		titleNames.add("年收入分类（家庭）");
		titleNames.add("家庭收入特点");
		titleNames.add("财务状况");

		return titleNames;
	}
}
