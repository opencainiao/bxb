package com.bxb.modules.infrastructure.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 用户dao
 * 
 * @author NBQ
 *
 */
@Repository("sysconsttypedao")
public class SysConstTypeDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "sysconsttype";
	}

}
