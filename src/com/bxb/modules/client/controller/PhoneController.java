package com.bxb.modules.client.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.bxb.modules.base.BaseController;
import com.bxb.modules.client.model.Phone;
import com.bxb.modules.client.service.IPhoneService;
import com.mongodb.DBObject;

/****
 * 电话管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/backend/phone")
public class PhoneController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(PhoneController.class);

	@Resource(name = "phoneService")
	private IPhoneService phoneService;

	/****
	 * 进入添加电话页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@ModelAttribute("phone") Phone phone,
			HttpServletRequest request, Model model) {

		model.addAttribute("default_phone_type_value", "1");
		model.addAttribute("default_phone_type_name", "个人");

		// 开启modelDriven
		return "admin/client/phone/add";
	}

	/****
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@Validated Phone phone, BindingResult br,
			HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);

		logger.debug("传入的电话对象\n{}", phone);

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}
		try {
			// 新增
			phone.setUseflg("1");
			String _id = this.phoneService.add(phone);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);

			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看所有系统电话 信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		return "admin/client/phone/list";
	}

	/****
	 * 查询系统电话信息（条件查询，查询多笔，按照系统电话码或名称）
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list_by_owner_id", method = RequestMethod.POST)
	@ResponseBody
	public Object listByOwnerId(Model model, HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);
		try {

			HttpServletRequestUtil.debugParams(request);

			String owner_id = HttpServletRequestUtil.getTrimParameter(request,
					"owner_id");

			return this.phoneService.findAllOnePageByOwnerId(owner_id);

		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看单个电话 信息
	 * 
	 * @param _id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}", method = RequestMethod.GET)
	public String detail(@PathVariable String _id, Model model) {

		Phone phone = this.phoneService.findOneByIdObject(_id);

		model.addAttribute("phone", phone);

		return "admin/client/phone/detail";
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

		Phone phone = this.phoneService.findOneByIdObject(_id);

		model.addAttribute("phone", phone);

		model.addAttribute("_id", _id);

		return "admin/client/phone/update";
	}

	/****
	 * 更新系统电话 信息，返回json给客户端
	 * 
	 * @param _id
	 * @param phone
	 * @param br
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable String _id, @Validated Phone phone,
			BindingResult br, HttpServletRequest request) {

		try {
			phone.set_id(_id);
			DBObject updateResult = this.phoneService.updatePart(null, phone);

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

			this.phoneService.RemoveOneByIdLogic(_id);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}
}
