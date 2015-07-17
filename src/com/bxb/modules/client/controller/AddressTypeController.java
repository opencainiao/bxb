package com.bxb.modules.client.controller;

import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxb.common.globalhandler.ErrorHandler;
import com.bxb.common.globalobj.RequestResult;
import com.bxb.common.util.HttpServletRequestUtil;
import com.bxb.common.util.RegexPatternUtil;
import com.bxb.modules.base.BaseController;
import com.bxb.modules.client.model.CommonType;
import com.bxb.modules.client.service.IAddressTypeService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 地址类型管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/backend/addresstype")
public class AddressTypeController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(AddressTypeController.class);

	@Resource(name = "addressTypeService")
	private IAddressTypeService addressTypeService;

	/****
	 * 进入添加地址类型页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@ModelAttribute("addresstype") CommonType addresstype,
			HttpServletRequest request) {

		// 开启modelDriven
		return "admin/client/addresstype/add";
	}

	/****
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@Validated CommonType addresstype, BindingResult br,
			HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);

		logger.debug("传入的地址类型对象\n{}", addresstype);

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}
		try {
			String userid = this.getUserId();

			if (StringUtil.isEmpty(userid)) {
				addresstype.setOwner_user_id("system");
				addresstype.setType_character("1");
			} else {
				addresstype.setOwner_user_id(userid);
				addresstype.setType_character("2");
			}

			// 1.校验是否已存在相同的类型码
			boolean isExist = this.addressTypeService.isExistSameTypename(
					addresstype.getType_name(), addresstype.getOwner_user_id());
			if (isExist) {
				RequestResult rr = new RequestResult();
				rr.setSuccess(false);
				rr.setMessage("已经存在名为【" + addresstype.getType_name().trim()
						+ "】的地址类型!");
				return rr;
			}

			// 1.校验是否已存在相同的类型值
			DBObject queryCondition = new BasicDBObject();
			queryCondition.put("address_type_value", addresstype
					.getType_value().trim());

			BasicDBList values = new BasicDBList();

			DBObject queryConditioncommon = new BasicDBObject();
			queryConditioncommon.put("owner_user_id", userid);
			DBObject queryConditionpersonal = new BasicDBObject();
			queryConditionpersonal.put("owner_user_id", "system");

			values.add(queryConditionpersonal);
			values.add(queryConditioncommon);
			queryCondition.put("$or", values);

			CommonType atInsystem = this.addressTypeService
					.findOneByWhere(queryCondition);

			if (atInsystem != null) {
				RequestResult rr = new RequestResult();
				rr.setSuccess(false);
				rr.setMessage("已经存在值为【" + addresstype.getType_value().trim()
						+ "】的地址类型!");
				return rr;
			}

			// 3.新增
			addresstype.setUseflg("1");
			String _id = this.addressTypeService.add(addresstype);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);

			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看所有系统地址类型 信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		return "admin/client/addresstype/list";
	}

	/****
	 * 查询系统地址类型信息（条件查询，查询多笔，按照系统地址类型码或名称）
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(Model model, HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);
		try {

			HttpServletRequestUtil.debugParams(request);

			String search_condition = request.getParameter("search_condition");
			if (StringUtil.isNotEmpty(search_condition)) {
				search_condition = search_condition.trim();
			}

			DBObject query = new BasicDBObject();

			if (StringUtil.isNotEmpty(search_condition)) {
				Pattern pattern = RegexPatternUtil
						.getLikePattern(search_condition);

				BasicDBList condList = new BasicDBList();

				condList.add(new BasicDBObject("address_type_name", pattern));
				condList.add(new BasicDBObject("address_type_value", pattern));

				query.put("$or", condList);
			}
			query.put("useflg", "1");

			DBObject sort = new BasicDBObject();
			sort.put("type_value", 1);
			DBObject returnFields = null;

			return this.addressTypeService.batchSearchPage(query, sort,
					returnFields);

		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看单个地址类型 信息
	 * 
	 * @param _id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}", method = RequestMethod.GET)
	public String detail(@PathVariable String _id, Model model) {

		CommonType addresstype = this.addressTypeService.findOneByIdObject(_id);

		model.addAttribute("addresstype", addresstype);

		return "admin/client/addresstype/detail";
	}

	/****
	 * 进入更新页面
	 * 
	 * @param zzdhid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.GET)
	public String update(@PathVariable String _id, Model model) {

		CommonType addresstype = this.addressTypeService.findOneByIdObject(_id);

		model.addAttribute("addresstype", addresstype);

		model.addAttribute("_id", _id);

		return "admin/client/addresstype/update";
	}

	/****
	 * 更新系统地址类型 信息，返回json给客户端
	 * 
	 * @param _id
	 * @param addresstype
	 * @param br
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable String _id,
			@Validated CommonType addresstype, BindingResult br,
			HttpServletRequest request) {

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}

		String name = addresstype.getType_name();
		if (StringUtil.isEmpty(name)) {
			return handleValidateFalse("地址类型名称不能为空");
		}

		name = name.trim();

		String type_value = addresstype.getType_value();
		if (StringUtil.isEmpty(type_value)) {
			return handleValidateFalse("地址类型值不能为空");
		}
		type_value = type_value.trim();

		CommonType sysconsttypeori = this.addressTypeService
				.findOneByIdObject(_id);
		String nameOri = sysconsttypeori.getType_name();
		String type_value_ori = sysconsttypeori.getType_value();

		if (name.equals(nameOri) && type_value.equals(type_value_ori)) {
			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		}

		String userid = this.getUserId();
		// 1.校验是否已存在相同的类型名
		boolean isExist = this.addressTypeService.isExistSameTypename(
				addresstype.getType_name(), userid, _id);
		if (isExist) {
			RequestResult rr = new RequestResult();
			rr.setSuccess(false);
			rr.setMessage("已经存在名称为【" + name + "】的地址类型!");
			return rr;
		}

		try {
			addresstype.set_id(_id);
			DBObject updateResult = this.addressTypeService.updatePart(null,
					addresstype);

			logger.debug("更新后的结果[{}]", updateResult);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 删除一条记录
	 * 
	 * @param zzdhid
	 * @return
	 */
	@RequestMapping(value = "/{_id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@PathVariable String _id, HttpServletRequest request) {

		try {

			this.addressTypeService.RemoveOneByIdLogic(_id);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/*--------------------------------------------------------------------------------*/
	/****
	 * 查看所有系统地址类型参照
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addresstype_reference", method = RequestMethod.GET)
	public String addresstype_reference(Model model) {

		return "admin/client/addresstype/addresstype_reference";
	}
}
