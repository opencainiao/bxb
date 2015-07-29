package com.bxb.modules.client.controller.app;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxb.common.globalhandler.ErrorHandler;
import com.bxb.common.globalobj.RequestResult;
import com.bxb.common.globalobj.ValidResult;
import com.bxb.common.util.HttpServletRequestUtil;
import com.bxb.common.util.JSONHelper;
import com.bxb.common.util.RegexPatternUtil;
import com.bxb.modules.base.BaseController;
import com.bxb.modules.client.model.Client;
import com.bxb.modules.client.model.ClientBaseInfo;
import com.bxb.modules.client.service.IClientService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * 用户管理Controller
 * 
 * @author NBQ
 *
 */
@Controller("mclientcontroller")
@RequestMapping("/app/client")
public class ClientController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(ClientController.class);

	@Resource(name = "clientService")
	private IClientService clientService;

	/****
	 * 添加客户信息<br>
	 * 返回生成的主键，如果有错误，返回错误
	 * 
	 * @param client
	 * @return
	 */
	private RequestResult addClient(Client client) {

		ValidResult validResult = this.validate(client);
		if (validResult.hasErrors()) {
			return ErrorHandler.getRequestResultFromValidResult(validResult);
		}

		RequestResult rr = new RequestResult();
		try {
			// 1.新增
			client.setUseflg("1");
			String _id = this.clientService.add(client);

			rr.setSuccess(true);
			rr.setMessage(_id);

			logger.debug("插入结果[{}]", rr);
			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 添加一组客户<br>
	 * 返回生成的主键，如果有错误，返回错误
	 * 
	 * @param clients
	 * @return
	 */
	private RequestResult[] addBatch(Client[] clients) {

		RequestResult[] rtnResult = new RequestResult[clients.length];

		for (int i = 0; i < clients.length; ++i) {
			Client client = clients[i];
			RequestResult addResult = addClient(client);

			rtnResult[i] = addResult;
		}

		return rtnResult;
	}

	/****
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(HttpServletRequest request, String data) {

		HttpServletRequestUtil.debugParams(request);

		try {
			Client[] clients = JSONHelper.parseArray(data, Client.class);

			return addBatch(clients);
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
			sort.put("typename", 1);
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

		Client client = this.clientService.findOneByIdObject(_id, false);

		model.addAttribute("client", client);

		model.addAttribute("base_prop_title", ClientBaseInfo.getTitles());
		model.addAttribute("base_prop_name", ClientBaseInfo.getTitleNames());

		return "front/client/client_info/full/detail";
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

		Client clientinfo = this.clientService.findOneByIdObject(_id, false);

		return clientinfo;
	}
}
