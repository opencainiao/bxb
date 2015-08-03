package com.bxb.modules.client.model.partinfo;

import java.util.ArrayList;
import java.util.List;

import com.bxb.modules.base.BaseModel;

public class ClientWorkInfo extends BaseModel {

	public static List<String> getTitles() {
		List<String> titles = new ArrayList<String>();

		titles.add("company");
		titles.add("company_nature_name");
		titles.add("trade_type_name");
		titles.add("career_type_name");
		titles.add("job_position_name");
		titles.add("job_level_name");

		return titles;
	}

	public static List<String> getTitleNames() {
		List<String> titleNames = new ArrayList<String>();

		titleNames.add("工作单位");
		titleNames.add("企业性质");
		titleNames.add("行业类型");
		titleNames.add("职业类型");
		titleNames.add("职位");
		titleNames.add("职级");
		
		return titleNames;
	}
}
