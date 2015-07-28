package com.bxb.modules.client.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.client.dao.AddressDao;
import com.bxb.modules.client.model.Address;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 地址服务实现
 * 
 * @author NBQ
 *
 */
@Service("addressService")
public class AddressService extends BaseService implements IAddressService {

	@Resource(name = "addressdao")
	private AddressDao addressdao;

	private static final Logger logger = LogManager
			.getLogger(AddressService.class);

	@Override
	public Address findOneByIdObject(String _id) {

		return this.addressdao.findOneByIdObject(_id, Address.class);
	}

	@Override
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort,
			DBObject returnFields) {
		return this.addressdao.batchSearchPage(queryCondition, sort,
				returnFields);
	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields) {
		return this.addressdao.batchSearchOnePage(query, sort, returnFields);
	}

	@Override
	public String add(Address address) {
		this.setCreateInfo(address);
		return this.addressdao.insertObj(address);
	}

	@Override
	public DBObject updatePart(DBObject returnFields, Address address) {

		DBObject toUpdate = makeUpdate(address);
		return this.addressdao.updateOneById(address.get_id_str(),
				returnFields, toUpdate);
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
		return this.addressdao.findAndRemoveOneById(_id);
	}

	@Override
	public DBObject RemoveOneByIdLogic(String _id) {

		DBObject updateSet = new BasicDBObject();
		this.setModifyInfo(updateSet);
		return this.addressdao.findAndRemoveOneByIdLogic(_id, updateSet);
	}

	@Override
	public List<DBObject> findAllByOwnerId(String ownerId) {

		DBObject queryCondition = new BasicDBObject();

		queryCondition.put("owner_id", ownerId);

		// 2.设置返回结果

		List<DBObject> allAddress = this.addressdao.batchSearch(queryCondition,
				null, null);

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

	@Override
	public List<String> add(List<Address> addresses,String ownerId) {
		List<String> ids = new ArrayList<String>();
		
		for(Address address:addresses){
			address.setOwner_id(ownerId);
			String addressid = this.add(address);
			
			ids.add(addressid);
		}
		
		return ids;
	}
}
