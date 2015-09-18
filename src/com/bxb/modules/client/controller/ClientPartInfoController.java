package com.bxb.modules.client.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxb.common.globalhandler.ErrorHandler;
import com.bxb.common.globalobj.RequestResult;
import com.bxb.common.util.WebContextUtil;
import com.bxb.modules.base.BaseController;
import com.bxb.modules.client.enumes.PartFlgEnum;
import com.bxb.modules.client.model.Client;
import com.bxb.modules.client.service.IClientService;
import com.bxb.modules.client.service.modifyclientinfo.IModifyClientInfoService;
import com.mongodb.DBObject;

/****
 * 客户部分信息管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/clientpartinfo")
public class ClientPartInfoController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(ClientPartInfoController.class);

	@Resource(name = "clientBaseInfoService")
	private IModifyClientInfoService clientBaseInfoService;

	@Resource(name = "clientService")
	private IClientService clientService;

	/****
	 * 进入更新页面
	 * 
	 * @param zzdhid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.GET)
	public String update(@PathVariable String _id, Model model, String part_flg) {

		Client client = this.clientService.findOneByIdObject(_id, true);

		model.addAttribute("client", client);
		model.addAttribute("_id", _id);

		model.addAttribute("owner_user_id", client.getOwner_user_id());

		return handlePart(model, client, part_flg);
	}

	/****
	 * 处理部分信息的内容
	 * 
	 * @param model
	 * @param client
	 * @param part_flg
	 * @return
	 */
	private String handlePart(Model model, Client client, String part_flg) {

		String path = null;

		if (part_flg.equals(PartFlgEnum.BASE.getCode())) {

			model.addAttribute("interesting_service",
					JsonUtil.toJsonStr(client.getInteresting_service()));
			
			path = "front/client/client_info/part/base/update";
		} else if (part_flg.equals(PartFlgEnum.FAMILLY.getCode())) {
			path = "front/client/client_info/part/familly/update";
		} else if (part_flg.equals(PartFlgEnum.WORK.getCode())) {
			path = "front/client/client_info/part/work/update";
		} else if (part_flg.equals(PartFlgEnum.INCOME.getCode())) {
			path = "front/client/client_info/part/income/update";
		} else if (part_flg.equals(PartFlgEnum.SOURCE.getCode())) {
			path = "front/client/client_info/part/source/update";
		} else if (part_flg.equals(PartFlgEnum.XG.getCode())) {
			path = "front/client/client_info/part/xg/update";
		} else if (part_flg.equals(PartFlgEnum.SERVICE.getCode())) {
			path = "front/client/client_info/part/service/update";
		} else if (part_flg.equals(PartFlgEnum.OTHER.getCode())) {
			path = "front/client/client_info/part/other/update";
		} else {
			path = "front/client/client_info/part/base/update";
		}

		return path;
	}

	/****
	 * 更新系统客户基本信息 信息，返回json给客户端
	 * 
	 * @param _id
	 * @param clientbaseinfo
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
			DBObject updateResult = this.clientBaseInfoService.updatePart(null,
					client);

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
	 * 查看单个客户基本信息 信息
	 * 
	 * @param _id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}", method = RequestMethod.POST)
	@ResponseBody
	public Object detail(@PathVariable String _id, String part_flg) {

		Client client = this.clientService.findOneByIdObject(_id, true);
		
		return client;
	}
}
