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
import com.bxb.modules.client.model.Address;
import com.bxb.modules.client.service.IAddressService;
import com.mongodb.DBObject;

/****
 * 地址管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/backend/address")
public class AddressController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(AddressController.class);

	@Resource(name = "addressService")
	private IAddressService addressService;

	/****
	 * 进入添加地址页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@ModelAttribute("address") Address address,
			HttpServletRequest request, Model model) {

		model.addAttribute("default_address_type_value", "1");
		model.addAttribute("default_address_type_name", "单位");

		// 开启modelDriven
		return "admin/client/address/add";
	}

	/****
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@Validated Address address, BindingResult br,
			HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);

		logger.debug("传入的地址对象\n{}", address);

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}
		try {

			// 新增
			address.setUseflg("1");
			String _id = this.addressService.add(address);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);

			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看所有系统地址 信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		return "admin/client/address/list";
	}

	/****
	 * 查询系统地址信息（条件查询，查询多笔，按照系统地址码或名称）
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

			return this.addressService.findAllOnePageByOwnerId(owner_id);

		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看单个地址 信息
	 * 
	 * @param _id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}", method = RequestMethod.GET)
	public String detail(@PathVariable String _id, Model model) {

		Address address = this.addressService.findOneByIdObject(_id);

		model.addAttribute("address", address);

		return "admin/client/address/detail";
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

		Address address = this.addressService.findOneByIdObject(_id);

		model.addAttribute("address", address);

		model.addAttribute("_id", _id);

		return "admin/client/address/update";
	}

	/****
	 * 更新系统地址 信息，返回json给客户端
	 * 
	 * @param _id
	 * @param address
	 * @param br
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable String _id, @Validated Address address,
			BindingResult br, HttpServletRequest request) {

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}

		try {
			address.set_id(_id);
			DBObject updateResult = this.addressService.updatePart(null,
					address);

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
			this.addressService.RemoveOneByIdLogic(_id);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}
}
