package com.bxb.modules.client.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 电话dao
 * 
 * @author NBQ
 *
 */
@Repository("phonedao")
public class PhoneDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "phone";
	}
}
