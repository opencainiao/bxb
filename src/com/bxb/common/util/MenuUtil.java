package com.bxb.common.util;

import com.bxb.Constant;
import com.bxb.modules.infrastructure.model.SysMenu;

public class MenuUtil {

	public static SysMenu createRootMenu() {
		SysMenu rootMnu = new SysMenu();
		rootMnu.setMenu_name(Constant.SYSNAM);
		rootMnu.setMenu_code("ROOT");
		rootMnu.setMenu_level(0);
		rootMnu.setUseflg("1");
		rootMnu.setLeaf_flg(false);

		return rootMnu;
	}
}
