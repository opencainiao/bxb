package com.bxb.modules.client.controller.app;

import java.util.Arrays;
import java.util.List;

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
import com.bxb.modules.base.BaseController;
import com.bxb.modules.client.model.Client;
import com.bxb.modules.client.service.IClientService;
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
	private RequestResult addBatch(Client[] clients) {

		RequestResult rr = new RequestResult();

		RequestResult[] addResults = new RequestResult[clients.length];

		int falseTimes = 0;
		for (int i = 0; i < clients.length; ++i) {
			Client client = clients[i];
			RequestResult addResult = addClient(client);

			if (addResult.getSuccess().equals("n")) {
				falseTimes++;
			}

			addResults[i] = addResult;
		}

		rr.setObjects(Arrays.asList(addResults));

		if (falseTimes > 0) {
			rr.setSuccess(false);
		} else {
			rr.setSuccess(true);
		}

		return rr;
	}

	/****
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(HttpServletRequest request, String data, String user_id) {

		HttpServletRequestUtil.debugParams(request);

		if (StringUtil.isEmpty(user_id)) {
			return this.handleValidateFalse("user_id不能为空");
		}

		try {
			Client[] clients = JSONHelper.parseArray(data, Client.class);

			return addBatch(clients);
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查询系统所有客户信息
	 * 
	 * @param model
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/list_by_userid", method = RequestMethod.POST)
	@ResponseBody
	public Object list_by_userid(Model model, HttpServletRequest request,
			String user_id) {

		if (StringUtil.isEmpty(user_id)) {
			return this.handleValidateFalse("user_id不能为空");
		}

		RequestResult rr = new RequestResult();

		try {

			List<DBObject> clients = this.clientService
					.findAllClientsByUserId(user_id);

			rr.setObjects(clients);
			rr.setSuccess(true);

			return rr;

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
	@ResponseBody
	public Object detail(@PathVariable String _id, Model model, String user_id) {

		if (StringUtil.isEmpty(user_id)) {
			return this.handleValidateFalse("user_id不能为空");
		}

		if (!this.isValidObjId(_id)) {
			return this.handleValidateFalse("非法的客户主键");
		}

		if (!this.isValidObjId(user_id)) {
			return this.handleValidateFalse("非法的用户");
		}

		RequestResult rr = new RequestResult();

		try {
			DBObject query = new BasicDBObject();
			query.put("owner_user_id", user_id);
			query.put("_id", _id);

			Client client = this.clientService.findOneByCondition(query, false);

			rr.setObject(client);
			rr.setSuccess(true);

			return rr;

		} catch (Exception e) {
			return this.handleException(e);
		}

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
	public Object delete(@PathVariable String _id, HttpServletRequest request,
			String user_id) {

		if (!this.isValidObjId(_id)) {
			return this.handleValidateFalse("非法的客户主键");
		}

		if (!this.isValidObjId(user_id)) {
			return this.handleValidateFalse("非法的用户");
		}

		try {

			DBObject result = this.clientService.RemoveOneByIdLogic(_id);
			RequestResult rr = new RequestResult();
			if (result == null) {
				rr.setSuccess(false);
				rr.setMessage("用户" + user_id + "不存在id为" + _id + "的客户");
				return rr;
			} else {
				rr.setSuccess(true);
				return rr;
			}
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

}
