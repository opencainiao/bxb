package com.bxb.modules.infrastructure.service;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.base.BaseService;
import com.bxb.modules.infrastructure.dao.CityDao;
import com.bxb.modules.infrastructure.model.City;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 城市服务实现
 * 
 * @author NBQ
 *
 */
@Service("cityService")
public class CityService extends BaseService implements ICityService {

	@Resource(name = "citydao")
	private CityDao citydao;

	private static final Logger logger = LogManager
			.getLogger(CityService.class);

	@Override
	public City findOneByIdObject(String _id) {

		return this.citydao.findOneByIdObject(_id, City.class);
	}

	@Override
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort,
			DBObject returnFields) {
		return this.citydao.batchSearchPage(queryCondition, sort, returnFields);
	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields) {
		return this.citydao.batchSearchOnePage(query, sort, returnFields);
	}

	@Override
	public String add(City city) {
		this.setCreateInfo(city);
		return this.citydao.insertObj(city);
	}

	@Override
	public DBObject updatePart(DBObject returnFields, City city) {

		DBObject toUpdate = makeUpdate(city);
		return this.citydao.updateOneById(city.get_id_str(), returnFields,
				toUpdate);
	}

	/****
	 * 转化对象为要更新的部分字段
	 * 
	 * @param update
	 * @return
	 */
	private DBObject makeUpdate(City city) {

		DBObject update = new BasicDBObject();
		DBObject updateSet = new BasicDBObject();

		// updateSet.put("typename", city.getTypename());

		this.setModifyInfo(updateSet);
		update.put("$set", updateSet);

		logger.debug("更新的对象信息\n{}", update);
		return update;
	}

	@Override
	public DBObject RemoveOneById(String _id) {
		return this.citydao.findAndRemoveOneById(_id);
	}

	@Override
	public DBObject RemoveOneByIdLogic(String _id) {

		DBObject updateSet = new BasicDBObject();
		this.setModifyInfo(updateSet);
		return this.citydao.findAndRemoveOneByIdLogic(_id, updateSet);
	}

	@Override
	public PageVO findChildrenByPIdOnePage(DBObject sort,
			DBObject returnFields, Integer parentId) {

		DBObject queryCondition = new BasicDBObject();
		queryCondition.put("parent_id", parentId);

		return this.batchSearchOnePage(queryCondition, sort, null);
	}

}
