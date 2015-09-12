package com.bxb.modules.infrastructure.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxb.common.util.HttpServletRequestUtil;
import com.bxb.modules.base.BaseController;
import com.bxb.modules.infrastructure.service.ICityService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 常量类型管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/backend/city")
public class CityController extends BaseController {

	private static final Logger logger = LogManager.getLogger(CityController.class);

	@Resource(name = "cityService")
	private ICityService cityService;

	/****
	 * 查询系统城市信息（按照父节点id）
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list_by_pid", method = RequestMethod.POST)
	@ResponseBody
	public Object list_by_pid(Model model, HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);
		try {

			HttpServletRequestUtil.debugParams(request);

			DBObject sort = new BasicDBObject();
			sort.put("code", 1);
			DBObject returnFields = null;

			String pId = HttpServletRequestUtil.getTrimParameter(request, "parent_id");
			Integer parent_id = null;
			if (pId != null) {
				parent_id = Integer.parseInt(pId);
			}
			return this.cityService.findChildrenByPIdOnePage(sort, returnFields, parent_id);

		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查询省城市信息（省一级）
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/provinces", method = RequestMethod.POST)
	@ResponseBody
	public Object provinces(Model model, HttpServletRequest request) {
		try {
			DBObject sort = new BasicDBObject();
			sort.put("code", 1);
			DBObject returnFields = null;

			return this.cityService.findChildrenByPId(sort, returnFields, null);
		} catch (Exception e) {
			return new String[] {};
			// return this.handleException(e);
		}
	}
	
	/****
	 * 查询省城市信息（按照父节点id）
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/citys", method = RequestMethod.POST)
	@ResponseBody
	public Object citys(Model model, HttpServletRequest request) {
		try {
			DBObject sort = new BasicDBObject();
			sort.put("code", 1);
			DBObject returnFields = null;
			
			String pId = HttpServletRequestUtil.getTrimParameter(request, "parent_id");
			Integer parent_id = null;
			if (pId != null) {
				parent_id = Integer.parseInt(pId);
			}

			List<DBObject> citys = this.cityService.findChildrenByPId(sort, returnFields, parent_id);
			return citys;
		} catch (Exception e) {
			return new String[] {};
			// return this.handleException(e);
		}
	}

	/*---------------------------------------------------------*/
	/****
	 * 省参照
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/province_reference", method = RequestMethod.GET)
	public String phonetype_reference(Model model) {

		return "admin/infrastructure/city/province_reference";
	}

	/****
	 * 市参照
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/city_reference", method = RequestMethod.GET)
	public String city_reference(Model model) {
		return "admin/infrastructure/city/city_reference";
	}

	/****
	 * 区参照
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/district_reference", method = RequestMethod.GET)
	public String district_reference(Model model) {

		return "admin/infrastructure/city/district_reference";
	}
}
