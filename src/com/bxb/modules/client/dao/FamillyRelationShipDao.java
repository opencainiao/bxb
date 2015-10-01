package com.bxb.modules.client.dao;

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * 家庭关系dao
 * 
 * @author NBQ
 *
 */
@Repository("famillyrelationshipdao")
public class FamillyRelationShipDao extends BaseDao {

	@Override
	public String getCollectionName() {
		return "familly_relationship";
	}
}
