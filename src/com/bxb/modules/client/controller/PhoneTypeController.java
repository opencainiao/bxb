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
import com.bxb.modules.client.service.IPhoneTypeService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 手机类型管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/backend/phonetype")
public class PhoneTypeController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(PhoneTypeController.class);

	@Resource(name = "phoneTypeService")
	private IPhoneTypeService phoneTypeService;

	/****
	 * 进入添加手机类型页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@ModelAttribute("phonetype") CommonType phonetype,
			HttpServletRequest request) {

		// 开启modelDriven
		return "admin/client/phonetype/add";
	}

	/****
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@Validated CommonType phonetype, BindingResult br,
			HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);

		logger.debug("传入的类型对象\n{}", phonetype);

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}
		try {
			phonetype.setOwnerInfo();

			// 1.校验是否已存在相同的类型值
			boolean isExist = this.phoneTypeService.isExistSameTypeValue(
					phonetype.getType_value(),
					phonetype.getOwner_user_id());
			if (isExist) {
				RequestResult rr = new RequestResult();
				rr.setSuccess(false);
				rr.setMessage("已经存在值为【"
						+ phonetype.getType_value().trim() + "】的电话类型!");
				return rr;
			}

			// 2.校验是否已存在相同的类型名
			isExist = this.phoneTypeService.isExistSameTypename(
					phonetype.getType_name(),
					phonetype.getOwner_user_id());
			if (isExist) {
				RequestResult rr = new RequestResult();
				rr.setSuccess(false);
				rr.setMessage("已经存在名为【" + phonetype.getType_name().trim()
						+ "】的电话类型!");
				return rr;
			}

			// 3.新增
			phonetype.setUseflg("1");
			String _id = this.phoneTypeService.add(phonetype);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);

			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看所有系统手机类型 信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		return "admin/client/phonetype/list";
	}

	/****
	 * 查询系统手机类型信息（条件查询，查询多笔，按照系统手机类型码或名称）
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

				condList.add(new BasicDBObject("typename", pattern));
				condList.add(new BasicDBObject("typecode", pattern));

				query.put("$or", condList);
			}
			query.put("useflg", "1");

			DBObject sort = new BasicDBObject();
			sort.put("type_value", 1);
			DBObject returnFields = null;

			return this.phoneTypeService.batchSearchPage(query, sort,
					returnFields);

		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看单个手机类型 信息
	 * 
	 * @param _id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}", method = RequestMethod.GET)
	public String detail(@PathVariable String _id, Model model) {

		CommonType phonetype = this.phoneTypeService.findOneByIdObject(_id);

		model.addAttribute("phonetype", phonetype);

		return "admin/client/phonetype/detail";
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

		CommonType phonetype = this.phoneTypeService.findOneByIdObject(_id);

		model.addAttribute("phonetype", phonetype);

		model.addAttribute("_id", _id);

		return "admin/client/phonetype/update";
	}

	/****
	 * 更新系统手机类型 信息，返回json给客户端
	 * 
	 * @param _id
	 * @param phonetype
	 * @param br
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable String _id,
			@Validated CommonType phonetype, BindingResult br,
			HttpServletRequest request) {

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}

		String name = phonetype.getType_name();
		if (StringUtil.isEmpty(name)) {
			return handleValidateFalse("电话类型名称不能为空");
		}

		name = name.trim();

		String type_value = phonetype.getType_value();
		if (StringUtil.isEmpty(type_value)) {
			return handleValidateFalse("电话类型值不能为空");
		}
		type_value = type_value.trim();

		CommonType phonetypeori = this.phoneTypeService.findOneByIdObject(_id);
		String nameOri = phonetypeori.getType_name();
		String type_value_ori = phonetypeori.getType_value();

		// 名字和值相同，则直接返回成功
		if (name.equals(nameOri) && type_value.equals(type_value_ori)) {
			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		}

		String userid = this.getUserId();

		// 1.校验是否已存在相同的类型码
		boolean isExist = this.phoneTypeService.isExistSameTypeValue(
				phonetype.getType_value(), userid, _id);
		if (isExist) {
			RequestResult rr = new RequestResult();
			rr.setSuccess(false);
			rr.setMessage("已经存在值为【" + phonetype.getType_value()
					+ "】的电话类型!");
			return rr;
		}

		// 2.校验是否已存在相同的类型名
		isExist = this.phoneTypeService.isExistSameTypename(
				phonetype.getType_name(), userid, _id);

		if (isExist) {
			RequestResult rr = new RequestResult();
			rr.setSuccess(false);
			rr.setMessage("已经存在名称为【" + name + "】的电话类型!");
			return rr;
		}

		try {
			phonetype.set_id(_id);
			DBObject updateResult = this.phoneTypeService.updatePart(null,
					phonetype);

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

			this.phoneTypeService.RemoveOneByIdLogic(_id);

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
	@RequestMapping(value = "/phonetype_reference", method = RequestMethod.GET)
	public String phonetype_reference(Model model) {

		return "admin/client/phonetype/phonetype_reference";
	}
}
