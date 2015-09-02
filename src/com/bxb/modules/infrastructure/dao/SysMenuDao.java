package com.bxb.modules.infrastructure.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 系统菜单dao
 * 
 * @author NBQ
 *
 */
@Repository("sysMenuDao")
public class SysMenuDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "sys_menu";
	}
}
