package com.bxb.modules.infrastructure.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 城市dao
 * 
 * @author NBQ
 *
 */
@Repository("citydao")
public class CityDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "city";
	}
}
