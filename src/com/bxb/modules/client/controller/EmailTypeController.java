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
import com.bxb.modules.client.service.IEmailTypeService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 邮箱类型管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/backend/emailtype")
public class EmailTypeController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(EmailTypeController.class);

	@Resource(name = "emailTypeService")
	private IEmailTypeService emailTypeService;

	/****
	 * 进入添加邮箱类型页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@ModelAttribute("emailtype") CommonType emailtype,
			HttpServletRequest request) {

		// 开启modelDriven
		return "admin/client/emailtype/add";
	}

	/****
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@Validated CommonType emailtype, BindingResult br,
			HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);

		logger.debug("传入的类型对象\n{}", emailtype);

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}
		try {
			String userid = this.getUserId();

			if (StringUtil.isEmpty(userid)) {
				emailtype.setOwner_user_id("system");
				emailtype.setType_character("1");
			} else {
				emailtype.setOwner_user_id(userid);
				emailtype.setType_character("2");
			}

			// 1.校验是否已存在相同的类型值
			boolean isExist = this.emailTypeService.isExistSameTypeValue(
					emailtype.getType_value(),
					emailtype.getOwner_user_id());
			if (isExist) {
				RequestResult rr = new RequestResult();
				rr.setSuccess(false);
				rr.setMessage("已经存在值为【"
						+ emailtype.getType_value().trim() + "】的邮箱类型!");
				return rr;
			}

			// 2.校验是否已存在相同的类型名
			isExist = this.emailTypeService.isExistSameTypename(
					emailtype.getType_name(),
					emailtype.getOwner_user_id());
			if (isExist) {
				RequestResult rr = new RequestResult();
				rr.setSuccess(false);
				rr.setMessage("已经存在名为【" + emailtype.getType_name().trim()
						+ "】的邮箱类型!");
				return rr;
			}

			// 3.新增
			emailtype.setUseflg("1");
			String _id = this.emailTypeService.add(emailtype);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);

			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看所有系统邮箱类型 信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		return "admin/client/emailtype/list";
	}

	/****
	 * 查询系统邮箱类型信息（条件查询，查询多笔，按照系统邮箱类型码或名称）
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

			return this.emailTypeService.batchSearchPage(query, sort,
					returnFields);

		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看单个邮箱类型 信息
	 * 
	 * @param _id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}", method = RequestMethod.GET)
	public String detail(@PathVariable String _id, Model model) {

		CommonType emailtype = this.emailTypeService.findOneByIdObject(_id);

		model.addAttribute("emailtype", emailtype);

		return "admin/client/emailtype/detail";
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

		CommonType emailtype = this.emailTypeService.findOneByIdObject(_id);

		model.addAttribute("emailtype", emailtype);

		model.addAttribute("_id", _id);

		return "admin/client/emailtype/update";
	}

	/****
	 * 更新系统邮箱类型 信息，返回json给客户端
	 * 
	 * @param _id
	 * @param emailtype
	 * @param br
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable String _id,
			@Validated CommonType emailtype, BindingResult br,
			HttpServletRequest request) {

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}

		String name = emailtype.getType_name();
		if (StringUtil.isEmpty(name)) {
			return handleValidateFalse("邮箱类型名称不能为空");
		}

		name = name.trim();

		String type_value = emailtype.getType_value();
		if (StringUtil.isEmpty(type_value)) {
			return handleValidateFalse("邮箱类型值不能为空");
		}
		type_value = type_value.trim();

		CommonType emailtypeori = this.emailTypeService.findOneByIdObject(_id);
		String nameOri = emailtypeori.getType_name();
		String type_value_ori = emailtypeori.getType_value();

		// 名字和值相同，则直接返回成功
		if (name.equals(nameOri) && type_value.equals(type_value_ori)) {
			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		}

		String userid = this.getUserId();

		// 1.校验是否已存在相同的类型码
		boolean isExist = this.emailTypeService.isExistSameTypeValue(
				emailtype.getType_value(), userid, _id);
		if (isExist) {
			RequestResult rr = new RequestResult();
			rr.setSuccess(false);
			rr.setMessage("已经存在值为【" + emailtype.getType_value()
					+ "】的邮箱类型!");
			return rr;
		}

		// 2.校验是否已存在相同的类型名
		isExist = this.emailTypeService.isExistSameTypename(
				emailtype.getType_name(), userid, _id);

		if (isExist) {
			RequestResult rr = new RequestResult();
			rr.setSuccess(false);
			rr.setMessage("已经存在名称为【" + name + "】的邮箱类型!");
			return rr;
		}

		try {
			emailtype.set_id(_id);
			DBObject updateResult = this.emailTypeService.updatePart(null,
					emailtype);

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

			this.emailTypeService.RemoveOneByIdLogic(_id);

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
	@RequestMapping(value = "/emailtype_reference", method = RequestMethod.GET)
	public String emailtype_reference(Model model) {

		return "admin/client/emailtype/emailtype_reference";
	}
}
