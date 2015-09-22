package com.bxb.modules.user.controller;

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
import com.bxb.modules.user.model.User;
import com.bxb.modules.user.service.IUserService;
import com.mongodb.DBObject;

/****
 * 个人信息
 * 
 * @author NBQ
 *
 */

@Controller
@RequestMapping("/profile")
public class ProfileController extends BaseController {

	@Resource(name = "userService")
	private IUserService userService;

	private static final Logger logger = LogManager
			.getLogger(ProfileController.class);

	/****
	 * 进入更新页面
	 * 
	 * @param zzdhid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.GET)
	public String update(@PathVariable String _id, Model model) {

		User user = this.userService.getUserInfById(_id);

		model.addAttribute("user", user);
		model.addAttribute("_id", _id);

		return "admin/user/profile/update";
	}

	/****
	 * 更新用户 信息，返回json给客户端
	 * 
	 * @param _id
	 * @param user
	 * @param br
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable String _id, @Validated User user,
			BindingResult br, HttpServletRequest request) {

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}

		try {
			DBObject updateResult = this.userService.updateProfile(null, user);

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
	 * 进入上传头像页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/to_upload_head_img", method = RequestMethod.GET)
	public String toUploadHeadImg(Model model) {

		return "admin/user/profile/pic_upload";
	}

}