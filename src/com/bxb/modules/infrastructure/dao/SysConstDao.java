package com.bxb.modules.infrastructure.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 用户dao
 * 
 * @author NBQ
 *
 */
@Repository("sysconstdao")
public class SysConstDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "sysconst";
	}
}
