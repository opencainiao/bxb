package com.bxb.modules.client.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 邮箱类型dao
 * 
 * @author NBQ
 *
 */
@Repository("emailtypedao")
public class EmailTypeDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "email_type";
	}
}
