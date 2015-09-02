/**********************************
 * project: WEBNIU31
 * package: org.webniu.com.frame.util
 * file   : DptController.java
 * version: 1.0.0 2012-4-05
 * author : NBQ
 * remark : 提供对zTree数据格式的转化
 */

package com.bxb.common.util;

import java.util.ArrayList;
import java.util.List;

import com.bxb.modules.infrastructure.model.SysMenu;

public class ZTreeUtil {

	public static List<SysMenu> transToZtreeMnuList(List<SysMenu> listIn) {

		List<SysMenu> rtnList = new ArrayList<SysMenu>();

		if (listIn == null) {
			return rtnList;
		}

		for (SysMenu sysmnu : listIn) {
			rtnList.add(sysmnu);
			if (sysmnu.getChild_menu_List() != null
					&& sysmnu.getChild_menu_List().size() > 0) {
				List<SysMenu> childrens = transToZtreeMnuList(sysmnu
						.getChild_menu_List());

				if (childrens != null && childrens.size() > 0) {
					for (SysMenu child : childrens) {
						rtnList.add(child);
					}
				}
			}
			sysmnu.setChild_menu_List(null);
		}

		return rtnList;
	}

	public static List<SysMenu> transToZtreeMnuList(SysMenu sysMnu) {

		List<SysMenu> rtnList = transToZtreeMnuList(sysMnu.getChild_menu_List());
		sysMnu.setChild_menu_List(null);
		rtnList.add(sysMnu);

		return rtnList;
	}

	/****
	 * 把JSON中的叶子节点转化成zTree可以识别的isParent标志
	 * 
	 * @param strIn
	 * @return
	 */
	public static String filterJsonStr(String strIn) {
		String rtnStr = strIn.replaceAll("\"leafflg\":\"1\"",
				"\"leafflg\":\"1\",\"isParent\":\"false\"");
		return rtnStr.replaceAll("\"leafflg\":\"0\"",
				"\"leafflg\":\"1\",\"isParent\":\"true\"");
	}
}
