package com.bxb.modules.infrastructure.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.bxb.modules.base.BaseModel;

/****
 * 系统菜单
 * 
 * @author NBQ
 */
public class SysMenu extends BaseModel {

	private String menu_code; // 菜单码
	private String menu_name; // 菜单名称
	private int menu_level; // 菜单级次
	private String sup_menu_code; // 上级菜单码
	private int menu_sno; // 同级菜单序号
	private String leaf_flg; // 是否叶子节点
	private String module_code; // 所属模块
	private String path; // 展示路径名
	private String remark; // 备注

	// 回显属性
	private String leaf_flg_name; // 是否叶子节点
	private String module_code_name; // 所属模块

	private List<SysMenu> child_menu_List; // 子菜单列表

	public boolean hasChildren() {
		if (this.child_menu_List != null && !this.child_menu_List.isEmpty()) {
			return true;
		}

		return false;
	}

	public void addChild(SysMenu sysmenu) {
		if (this.child_menu_List == null) {
			this.child_menu_List = new ArrayList<SysMenu>();
		}

		this.child_menu_List.add(sysmenu);
	}

	@SuppressWarnings("unchecked")
	public void sortchildren() {
		List<SysMenu> children = this.getChild_menu_List();
		SysMnuComparator sysMnuComparator = new SysMnuComparator();
		Collections.sort(children, sysMnuComparator);

		for (SysMenu sysmenu : children) {
			if (sysmenu.hasChildren()) {
				sysmenu.sortchildren();
			}
		}
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMenu_code() {
		return menu_code;
	}

	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getMenu_level() {
		return menu_level;
	}

	public void setMenu_level(int menu_level) {
		this.menu_level = menu_level;
	}

	public String getSup_menu_code() {
		return sup_menu_code;
	}

	public void setSup_menu_code(String sup_menu_code) {
		this.sup_menu_code = sup_menu_code;
	}

	public int getMenu_sno() {
		return menu_sno;
	}

	public void setMenu_sno(int menu_sno) {
		this.menu_sno = menu_sno;
	}

	public String getLeaf_flg() {
		return leaf_flg;
	}

	public boolean isLeaf() {
		if (this.leaf_flg.equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	public void setLeaf_flg(boolean isLeaf) {
		if (isLeaf) {
			this.leaf_flg = "1";
			this.leaf_flg_name = "没有子节点";
		} else {
			this.leaf_flg = "0";
			this.leaf_flg_name = "有子节点";
		}
	}

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLeaf_flg_name() {
		return leaf_flg_name;
	}

	public void setLeaf_flg_name(String leaf_flg_name) {
		this.leaf_flg_name = leaf_flg_name;
	}

	public String getModule_code_name() {
		return module_code_name;
	}

	public void setModule_code_name(String module_code_name) {
		this.module_code_name = module_code_name;
	}

	public List<SysMenu> getChild_menu_List() {
		return child_menu_List;
	}

	public void setChild_menu_List(List<SysMenu> child_menu_List) {
		this.child_menu_List = child_menu_List;
	}

	@SuppressWarnings("rawtypes")
	class SysMnuComparator implements Comparator {

		public int compare(Object arg0, Object arg1) {
			SysMenu mnu0 = (SysMenu) arg0;
			SysMenu mnu1 = (SysMenu) arg1;

			int lvlno0 = mnu0.getMenu_sno();
			int lvlno1 = mnu1.getMenu_sno();

			return (lvlno0 - lvlno1);
		}
	}

}
