package com.bxb.modules.client.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 手机类型dao
 * 
 * @author NBQ
 *
 */
@Repository("phonetypedao")
public class PhoneTypeDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "phone_type";
	}
}
