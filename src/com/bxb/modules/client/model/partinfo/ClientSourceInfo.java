package com.bxb.modules.client.model.partinfo;

import java.util.ArrayList;
import java.util.List;

import com.bxb.modules.base.BaseModel;

public class ClientSourceInfo extends BaseModel {

	public static List<String> getTitles() {
		List<String> titles = new ArrayList<String>();

		titles.add("source_type_name");
		titles.add("introducer_name");
		titles.add("introducer_relationship_name");
		titles.add("introducer_closeness");
		titles.add("introducer_evaluation");

		return titles;
	}

	public static List<String> getTitleNames() {
		List<String> titleNames = new ArrayList<String>();

		titleNames.add("客户来源");
		titleNames.add("介绍人");
		titleNames.add("与介绍人关系");
		titleNames.add("与介绍人亲密度");
		titleNames.add("介绍人评价");
		
		return titleNames;
	}
}
