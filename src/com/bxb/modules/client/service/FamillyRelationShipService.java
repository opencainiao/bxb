package com.bxb.modules.client.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.FamillyRelationShipDao;
import com.bxb.modules.client.model.Address;
import com.bxb.modules.infrastructure.model.ClientRelationShip;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 地址服务实现
 * 
 * @author NBQ
 *
 */
@Service("famillyRelationShipService")
public class FamillyRelationShipService extends BaseService implements IFamillyRelationShip {

	@Resource(name = "famillyrelationshipdao")
	private FamillyRelationShipDao famillyRelationShipDao;

	private static final Logger logger = LogManager.getLogger(FamillyRelationShipService.class);

	@Override
	public Address findOneByIdObject(String _id) {

		return this.famillyRelationShipDao.findOneByIdObject(_id, Address.class);
	}

	@Override
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort, DBObject returnFields) {
		return this.famillyRelationShipDao.batchSearchPage(queryCondition, sort, returnFields);
	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort, DBObject returnFields) {
		return this.famillyRelationShipDao.batchSearchOnePage(query, sort, returnFields);
	}

	@Override
	public DBObject updatePart(DBObject returnFields, Address address) {

		DBObject toUpdate = makeUpdate(address);
		return this.famillyRelationShipDao.updateOneById(address.get_id_m(), returnFields, toUpdate);
	}

	/****
	 * 转化对象为要更新的部分字段
	 * 
	 * @param update
	 * @return
	 */
	private DBObject makeUpdate(Address address) {

		DBObject update = new BasicDBObject();
		DBObject updateSet = new BasicDBObject();

		updateSet.put("type_value", address.getType_value());
		updateSet.put("type_name", address.getType_name());
		updateSet.put("province", address.getProvince());
		updateSet.put("city", address.getCity());
		updateSet.put("district", address.getDistrict());
		updateSet.put("detail_address", address.getDetail_address());
		updateSet.put("mainflg", address.getMainflg());

		this.setModifyInfo(updateSet);
		update.put("$set", updateSet);

		logger.debug("更新的对象信息\n{}", update);
		return update;
	}

	@Override
	public DBObject RemoveOneById(String _id) {
		return this.famillyRelationShipDao.findAndRemoveOneById(_id);
	}

	@Override
	public DBObject RemoveOneByIdLogic(String _id) {

		DBObject updateSet = new BasicDBObject();
		this.setModifyInfo(updateSet);
		return this.famillyRelationShipDao.findAndRemoveOneByIdLogic(_id, updateSet);
	}

	@Override
	public List<DBObject> findAllByOwnerId(String ownerId) {

		DBObject queryCondition = new BasicDBObject();

		queryCondition.put("owner_id", ownerId);

		// 2.设置返回结果

		List<DBObject> allAddress = this.famillyRelationShipDao.findBatchDbOjbect(queryCondition, null, null);

		return allAddress;
	}

	@Override
	public PageVO findAllOnePageByOwnerId(String ownerId) {

		DBObject queryCondition = new BasicDBObject();
		queryCondition.put("owner_id", ownerId);

		DBObject sort = new BasicDBObject();
		sort.put("type_value", 1);

		return batchSearchOnePage(queryCondition, sort, null);
	}

	/****
	 * 添加关系
	 */
	@Override
	public String add(ClientRelationShip clientrelationship) {
		return this.famillyRelationShipDao.insertObj(clientrelationship);
	}
}
