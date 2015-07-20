package com.bxb.modules.client.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.bxb.modules.base.BaseController;
import com.bxb.modules.client.model.ClientBaseInfo;
import com.bxb.modules.client.service.IClientBaseInfoService;
import com.mongodb.DBObject;

/****
 * 客户基本信息管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/clientbaseinfo")
public class ClientBaseInfoController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(ClientBaseInfoController.class);

	@Resource(name = "clientBaseInfoService")
	private IClientBaseInfoService clientBaseInfoService;

	/****
	 * 查看单个客户基本信息 信息
	 * 
	 * @param _id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}", method = RequestMethod.GET)
	public String detail(@PathVariable String _id, Model model) {

		ClientBaseInfo clientbaseinfo = this.clientBaseInfoService
				.findOneByIdObject(_id);

		model.addAttribute("clientbaseinfo", clientbaseinfo);

		return "front/client/client_info/base/detail";
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

		ClientBaseInfo clientbaseinfo = this.clientBaseInfoService
				.findOneByIdObject(_id);

		model.addAttribute("clientbaseinfo", clientbaseinfo);

		model.addAttribute("_id", _id);

		return "front/client/client_info/base/update";
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
	public Object update(@PathVariable String _id,
			@Validated ClientBaseInfo clientbaseinfo, BindingResult br,
			HttpServletRequest request) {

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}

		try {
			clientbaseinfo.set_id(_id);
			DBObject updateResult = this.clientBaseInfoService.updatePart(null,
					clientbaseinfo);

			logger.debug("更新后的结果[{}]", updateResult);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}
}
