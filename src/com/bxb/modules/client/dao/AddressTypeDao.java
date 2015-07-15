package com.bxb.modules.client.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 地址dao
 * 
 * @author NBQ
 *
 */
@Repository("addresstypedao")
public class AddressTypeDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "address_type";
	}
}
