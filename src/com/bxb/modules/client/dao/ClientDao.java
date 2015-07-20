package com.bxb.modules.client.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 用户dao
 * 
 * @author NBQ
 *
 */
@Repository("clientdao")
public class ClientDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "client";
	}
}
