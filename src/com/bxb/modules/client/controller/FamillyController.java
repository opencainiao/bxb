package com.bxb.modules.client.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.JsonUtil;
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
import com.bxb.common.globalobj.WorkbookConfig;
import com.bxb.common.util.ExportUtils;
import com.bxb.common.util.HttpServletRequestUtil;
import com.bxb.common.util.WebContextUtil;
import com.bxb.common.util.propertyeditor.CustomerDoubleEditor;
import com.bxb.common.util.propertyeditor.CustomerIntegerEditor;
import com.bxb.common.util.propertyeditor.CustomerListEditor;
import com.bxb.modules.base.BaseController;
import com.bxb.modules.client.enumes.PartFlgEnum;
import com.bxb.modules.client.model.Address;
import com.bxb.modules.client.model.Client;
import com.bxb.modules.client.model.Phone;
import com.bxb.modules.client.service.IFamillyRelationShip;
import com.bxb.modules.client.service.modifyclientinfo.IModifyClientInfoService;
import com.bxb.modules.infrastructure.model.ClientRelationShip;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 家庭成员管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/front/familly")
public class FamillyController extends BaseController {

	private static final Logger logger = LogManager.getLogger(FamillyController.class);

	@Resource(name = "famillyRelationShipService")
	private IFamillyRelationShip famillyRelationShipService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.TYPE, new CustomerIntegerEditor());
		binder.registerCustomEditor(Double.TYPE, new CustomerDoubleEditor());
		binder.registerCustomEditor(List.class, new CustomerListEditor());
	}

	/****
	 * 进入添加家庭成员页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@ModelAttribute("clientrelationship") ClientRelationShip clientrelationship,
			HttpServletRequest request, Model model, String user_id, String user_name,
			String user_sex) {

		clientrelationship.setF_id(user_id);
		clientrelationship.setF_name(user_name);
		clientrelationship.setF_sex(user_sex);

		// 开启modelDriven
		return "front/client/client_info/full/add_familly";
	}

	/****
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@Validated ClientRelationShip clientrelationship, BindingResult br,
			HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);

		logger.debug("传入的ClientRelationShip对象\n{}", clientrelationship);

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}
		try {

			// 2.新增
			String _id = this.famillyRelationShipService.add(clientrelationship);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);

			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
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
	public Object list(Model model, HttpServletRequest request, String client_id) {

		HttpServletRequestUtil.debugParams(request);
		try {

			DBObject query = new BasicDBObject();
			query.put("f_id", client_id);

			DBObject sort = new BasicDBObject();
			sort.put("client_name_full_py", 1);

			DBObject returnFields = null;

			return this.famillyRelationShipService.batchSearchPage(query, sort, returnFields);

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

		// Client client =
		// this.famillyRelationShipService.findOneByIdObject(_id, true);
		//
		// model.addAttribute("client", client);
		//
		// model.addAttribute("base_prop_title", ClientBaseInfo.getTitles());
		// model.addAttribute("base_prop_name", ClientBaseInfo.getTitleNames());
		//
		// model.addAttribute("family_prop_title",
		// ClientFamilyInfo.getTitles());
		// model.addAttribute("family_prop_name",
		// ClientFamilyInfo.getTitleNames());
		//
		// model.addAttribute("income_prop_title",
		// ClientIncomeInfo.getTitles());
		// model.addAttribute("income_prop_name",
		// ClientIncomeInfo.getTitleNames());
		//
		// model.addAttribute("source_prop_title",
		// ClientSourceInfo.getTitles());
		// model.addAttribute("source_prop_name",
		// ClientSourceInfo.getTitleNames());
		//
		// model.addAttribute("work_prop_title", ClientWorkInfo.getTitles());
		// model.addAttribute("work_prop_name", ClientWorkInfo.getTitleNames());
		//
		// model.addAttribute("xg_prop_title", ClientXgInfo.getTitles());
		// model.addAttribute("xg_prop_name", ClientXgInfo.getTitleNames());

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

		// Client client =
		// this.famillyRelationShipService.findOneByIdObject(_id);

		// model.addAttribute("client", client);

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
	public Object update(@PathVariable String _id, @Validated Client client, BindingResult br,
			HttpServletRequest request) {

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}

		try {
			client.set_id(_id);
			// DBObject updateResult =
			// this.famillyRelationShipService.updatePart(null, client);

			// logger.debug("更新后的结果[{}]", updateResult);

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

			this.famillyRelationShipService.RemoveOneById(_id);

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

		// Client clientinfo =
		// this.famillyRelationShipService.findOneByIdObject(_id, true);

		// return clientinfo;
		return null;
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
	public Object update_part(@PathVariable String _id, Client client, HttpServletRequest request,
			String part_flg) {

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
		String interesting_services = request.getParameter("interesting_service");

		List<Phone> phones = JsonUtil.getGson().fromJson(phone_info, new TypeToken<List<Phone>>() {
		}.getType());

		List<Address> addresses = JsonUtil.getGson().fromJson(address_info,
				new TypeToken<List<Address>>() {
				}.getType());

		List<String> interesting_service = JsonUtil.getGson().fromJson(interesting_services,
				new TypeToken<List<String>>() {
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

	/****
	 * 下载整表,单sheet文件
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/download_clients", method = RequestMethod.POST)
	public void single(Model model, HttpServletRequest request, HttpServletResponse response,
			String titles, String fields, String fileName) {

		logger.debug("下载单sheet文件");
		HttpServletRequestUtil.debugParams(request);

		try {
			// 获取类
			// List list =
			// this.famillyRelationShipService.downLoadAllClientByUserId(this.getUserId());

			String[] titleNames = Client.getTitlesForDownLoad();
			String[] fieldNames = Client.getFieldsForDownLoad();
			Map<String, Integer> fieldWidths = Client.getWidthsForDownLoad();
			String sheetName = "sheet0";

			WorkbookConfig wcg = new WorkbookConfig();
			wcg.setWorkbookName(fileName);
			wcg.addSheetName(sheetName);
			wcg.addSheetField(sheetName, fieldNames);
			wcg.addSheetTitle(sheetName, titleNames);
			wcg.addSheetWidth(sheetName, fieldWidths);

			// HSSFWorkbook wb = ExportUtils.createSingSheetHSSFWorkbook(wcg,
			// list);

			ExportUtils.setHeader(response, fileName);
			// 获取输出流，写入excel 并关闭
			ServletOutputStream out = response.getOutputStream();

			// wb.write(out);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
