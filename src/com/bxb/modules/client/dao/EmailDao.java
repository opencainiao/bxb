package com.bxb.modules.client.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 电话dao
 * 
 * @author NBQ
 *
 */
@Repository("emaildao")
public class EmailDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "email";
	}
}
