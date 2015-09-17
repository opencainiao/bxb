package com.bxb.modules.client.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.JsonUtil;
import org.mou.common.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxb.common.globalhandler.ErrorHandler;
import com.bxb.common.globalobj.RequestResult;
import com.bxb.common.util.HttpServletRequestUtil;
import com.bxb.common.util.JSONHelper;
import com.bxb.common.util.WebContextUtil;
import com.bxb.common.util.propertyeditor.CustomerDoubleEditor;
import com.bxb.common.util.propertyeditor.CustomerIntegerEditor;
import com.bxb.common.util.propertyeditor.CustomerListEditor;
import com.bxb.modules.base.BaseController;
import com.bxb.modules.client.enumes.PartFlgEnum;
import com.bxb.modules.client.model.Address;
import com.bxb.modules.client.model.Client;
import com.bxb.modules.client.model.Phone;
import com.bxb.modules.client.model.partinfo.ClientBaseInfo;
import com.bxb.modules.client.model.partinfo.ClientFamilyInfo;
import com.bxb.modules.client.model.partinfo.ClientIncomeInfo;
import com.bxb.modules.client.model.partinfo.ClientSourceInfo;
import com.bxb.modules.client.model.partinfo.ClientWorkInfo;
import com.bxb.modules.client.model.partinfo.ClientXgInfo;
import com.bxb.modules.client.service.IClientService;
import com.bxb.modules.client.service.modifyclientinfo.IModifyClientInfoService;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 用户管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/front/client")
public class ClientController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(ClientController.class);

	@Resource(name = "clientService")
	private IClientService clientService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.TYPE, new CustomerIntegerEditor());
		binder.registerCustomEditor(Double.TYPE, new CustomerDoubleEditor());
		binder.registerCustomEditor(List.class, new CustomerListEditor());
	}

	/****
	 * 进入添加用户页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@ModelAttribute("client") Client client,
			HttpServletRequest request, Model model) {

		String userId = this.getUserId();
		if (StringUtil.isEmpty(userId)) {
			userId = request.getParameter("owner_user_id");
		}

		model.addAttribute("owner_user_id", userId);

		// 开启modelDriven
		return "front/client/client_info/full/add";
	}

	/****
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@Validated Client client, BindingResult br,
			HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);

		String phone_info = request.getParameter("phone_info");
		String address_info = request.getParameter("address_info");
		String interesting_services = request
				.getParameter("interesting_service");

		List<Phone> phones = JsonUtil.getGson().fromJson(phone_info,
				new TypeToken<List<Phone>>() {
				}.getType());

		List<Address> addresses = JsonUtil.getGson().fromJson(address_info,
				new TypeToken<List<Address>>() {
				}.getType());

		List<String> interesting_service = JsonUtil.getGson().fromJson(
				interesting_services, new TypeToken<List<String>>() {
				}.getType());

		client.setInteresting_service(interesting_service);
		client.setPhone_info(phones);
		client.setAddress_info(addresses);

		logger.debug("传入的用户对象\n{}", client);

		// String userId = this.getUserId();
		// if (StringUtil.isEmpty(userId)) {
		// return this.handleValidateFalse("所属用户id不能为空");
		// }
		//
		// client.setOwner_user_id(userId);

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}
		try {
			// 1.校验是否已存在相同的类型码
			// boolean isExist = this.clientService
			// .isExistSameTypecode(client.getTypecode());
			// if (isExist) {
			// RequestResult rr = new RequestResult();
			// rr.setSuccess(false);
			// rr.setMessage("已经存在类型码【" + client.getTypecode().trim()
			// + "】的用户!");
			// return rr;
			// }

			// 2.新增
			client.setUseflg("1");
			String _id = this.clientService.add(client);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);

			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看所有系统用户 信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		return "front/client/client_info/full/list";
	}

	/****
	 * 查询系统用户信息（条件查询，查询多笔，按照系统用户码或名称）
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(Model model, HttpServletRequest request, String userId) {

		HttpServletRequestUtil.debugParams(request);
		try {

			DBObject query = new BasicDBObject();
			if (StringUtil.isNotEmpty(userId)) {
				query.put("owner_user_id", userId);
			}
			query.put("useflg", "1");

			DBObject sort = new BasicDBObject();
			sort.put("client_name_full_py", 1);

			DBObject returnFields = null;

			return this.clientService
					.batchSearchPage(query, sort, returnFields);

		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看单个用户 信息
	 * 
	 * @param _id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}", method = RequestMethod.GET)
	public String detail(@PathVariable String _id, Model model) {

		Client client = this.clientService.findOneByIdObject(_id, true);

		model.addAttribute("client", client);

		model.addAttribute("base_prop_title", ClientBaseInfo.getTitles());
		model.addAttribute("base_prop_name", ClientBaseInfo.getTitleNames());

		model.addAttribute("family_prop_title", ClientFamilyInfo.getTitles());
		model.addAttribute("family_prop_name", ClientFamilyInfo.getTitleNames());

		model.addAttribute("income_prop_title", ClientIncomeInfo.getTitles());
		model.addAttribute("income_prop_name", ClientIncomeInfo.getTitleNames());

		model.addAttribute("source_prop_title", ClientSourceInfo.getTitles());
		model.addAttribute("source_prop_name", ClientSourceInfo.getTitleNames());

		model.addAttribute("work_prop_title", ClientWorkInfo.getTitles());
		model.addAttribute("work_prop_name", ClientWorkInfo.getTitleNames());

		model.addAttribute("xg_prop_title", ClientXgInfo.getTitles());
		model.addAttribute("xg_prop_name", ClientXgInfo.getTitleNames());

		return "front/client/client_info/full/detail";
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

		Client client = this.clientService.findOneByIdObject(_id, true);

		model.addAttribute("client", client);

		model.addAttribute("_id", _id);

		return "front/client/client_info/full/update";
	}

	/****
	 * 更新系统用户 信息，返回json给客户端
	 * 
	 * @param _id
	 * @param client
	 * @param br
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable String _id, @Validated Client client,
			BindingResult br, HttpServletRequest request) {

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}

		try {
			client.set_id(_id);
			DBObject updateResult = this.clientService.updatePart(null, client);

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

			this.clientService.RemoveOneByIdLogic(_id);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看单个客户基本信息 信息
	 * 
	 * @param _id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}", method = RequestMethod.POST)
	@ResponseBody
	public Object detail(@PathVariable String _id) {

		Client clientinfo = this.clientService.findOneByIdObject(_id, true);

		return clientinfo;
	}

	/****
	 * 更新系统客户 信息，返回json给客户端
	 * 
	 * @param _id
	 * @param data
	 * @param user_id
	 * @param request
	 * @param part_flg
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update_part", method = RequestMethod.POST)
	@ResponseBody
	public Object update_part(@PathVariable String _id, Client client,
			HttpServletRequest request, String part_flg) {
		
		String user_id = client.getOwner_user_id();
		if (!this.isValidObjId(_id)) {
			return this.handleValidateFalse("非法的客户主键");
		}

		if (!this.isValidObjId(user_id)) {
			return this.handleValidateFalse("非法的用户");
		}

		if (!PartFlgEnum.isValidPartFlg(part_flg)) {
			return this.handleValidateFalse("非法的更新参数part_flg");
		}
		
		client.set_id_m(_id);
		
		String phone_info = request.getParameter("phone_info");
		String address_info = request.getParameter("address_info");
		String interesting_services = request
				.getParameter("interesting_service");

		List<Phone> phones = JsonUtil.getGson().fromJson(phone_info,
				new TypeToken<List<Phone>>() {
				}.getType());

		List<Address> addresses = JsonUtil.getGson().fromJson(address_info,
				new TypeToken<List<Address>>() {
				}.getType());

		List<String> interesting_service = JsonUtil.getGson().fromJson(
				interesting_services, new TypeToken<List<String>>() {
				}.getType());

		client.setInteresting_service(interesting_service);
		client.setPhone_info(phones);
		client.setAddress_info(addresses);

		logger.debug("传入的用户对象\n{}", client);

		try {
			IModifyClientInfoService modifyClientService = getModifyService(part_flg);
			DBObject updateResult = modifyClientService.updatePart(null, client);

			logger.debug("更新后的结果[{}]", updateResult);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	private IModifyClientInfoService getModifyService(String part_flg) {

		IModifyClientInfoService modifyClientInfoService = null;

		if (part_flg.equals(PartFlgEnum.BASE.getCode())) {
			modifyClientInfoService = (IModifyClientInfoService) WebContextUtil
					.getBean("clientBaseInfoService");

		} else if (part_flg.equals(PartFlgEnum.FAMILLY.getCode())) {
			modifyClientInfoService = (IModifyClientInfoService) WebContextUtil
					.getBean("clientFamilyInfoService");

		} else if (part_flg.equals(PartFlgEnum.WORK.getCode())) {
			modifyClientInfoService = (IModifyClientInfoService) WebContextUtil
					.getBean("clientWorkInfoService");

		} else if (part_flg.equals(PartFlgEnum.INCOME.getCode())) {
			modifyClientInfoService = (IModifyClientInfoService) WebContextUtil
					.getBean("clientIncomeInfoService");

		} else if (part_flg.equals(PartFlgEnum.SOURCE.getCode())) {
			modifyClientInfoService = (IModifyClientInfoService) WebContextUtil
					.getBean("clientSourceInfoService");

		} else if (part_flg.equals(PartFlgEnum.XG.getCode())) {
			modifyClientInfoService = (IModifyClientInfoService) WebContextUtil
					.getBean("clientXgInfoService");

		} else if (part_flg.equals(PartFlgEnum.SERVICE.getCode())) {
			modifyClientInfoService = (IModifyClientInfoService) WebContextUtil
					.getBean("clientServiceInfoService");

		} else if (part_flg.equals(PartFlgEnum.OTHER.getCode())) {
			modifyClientInfoService = (IModifyClientInfoService) WebContextUtil
					.getBean("clientOtherInfoService");
		}

		return modifyClientInfoService;
	}
}
