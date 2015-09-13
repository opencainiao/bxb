package com.bxb.modules.client.model.partinfo;

import java.util.ArrayList;
import java.util.List;

import com.bxb.modules.base.BaseModel;

public class ClientXgInfo extends BaseModel {

	public static List<String> getTitles() {
		List<String> titles = new ArrayList<String>();

		titles.add("birth_ages_name");
		titles.add("age_group_name");
		titles.add("constellation");
		titles.add("blood_group");
		titles.add("temperament_type");
		titles.add("pdp_type");
		titles.add("hobbies");

		return titles;
	}

	public static List<String> getTitleNames() {
		List<String> titleNames = new ArrayList<String>();

		titleNames.add("出生年代");
		titleNames.add("年龄段");
		titleNames.add("星座");
		titleNames.add("血型");
		titleNames.add("性格特点");
		titleNames.add("PDP类型");
		titleNames.add("兴趣爱好");
		return titleNames;
	}
}
