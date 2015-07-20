package com.bxb.modules.client.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 客户基本信息dao
 * 
 * @author NBQ
 *
 */
@Repository("clientbaseinfodao")
public class ClientBaseInfoDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "client";
	}
}
