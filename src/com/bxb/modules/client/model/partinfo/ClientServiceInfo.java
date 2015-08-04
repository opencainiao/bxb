package com.bxb.modules.client.model.partinfo;

import java.util.ArrayList;
import java.util.List;

import com.bxb.modules.base.BaseModel;

public class ClientServiceInfo extends BaseModel {

	public static List<String> getTitles() {
		List<String> titles = new ArrayList<String>();

		titles.add("interesting_service_name");

		return titles;
	}

	public static List<String> getTitleNames() {
		List<String> titleNames = new ArrayList<String>();
		titleNames.add("关注的服务");
		return titleNames;
	}
}
