package com.bxb.modules.user.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bxb.common.globalhandler.ErrorHandler;
import com.bxb.common.globalobj.RequestResult;
import com.bxb.common.util.HttpServletRequestUtil;
import com.bxb.common.util.ValidateUtil;
import com.bxb.modules.base.BaseController;
import com.bxb.modules.global.model.Attachment;
import com.bxb.modules.global.service.IFileUpload;
import com.bxb.modules.global.service.ThumbParam;
import com.bxb.modules.global.service.ThumbType;
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

	@Resource(name = "fileUplodService")
	private IFileUpload fileUplodService;

	private static final Logger logger = LogManager.getLogger(ProfileController.class);

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
	public Object update(@PathVariable String _id, User user, BindingResult br,
			HttpServletRequest request) {

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}

		user.set_id_m(request.getParameter("_id_m"));
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

	/****
	 * 更新用户头像,上传一个要裁剪的图片到mongo数据库。 只存储裁剪后的图片
	 * 
	 * @param _id
	 * @param request
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param w
	 * @param h
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(value = "/{_id}/update_head_img", method = RequestMethod.POST)
	@ResponseBody
	public Object upDateHeadImg(@PathVariable String _id, HttpServletRequest request, String x1,
			String y1, String x2, String y2, String w, String h)
					throws UnsupportedEncodingException {

		HttpServletRequestUtil.debugParams(request);

		Attachment attach = null;
		Map<String, Object> result = new HashMap<String, Object>();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		ThumbParam tp = new ThumbParam();
		tp.setWidth((int) Double.parseDouble(w));
		tp.setHeight((int) Double.parseDouble(h));

		boolean isCompress = false;

		if (StringUtil.isNotEmpty(x1) && ValidateUtil.isNumericOrDouble(x1)) {

			tp.setX1(Double.parseDouble(x1));
			tp.setY1(Double.parseDouble(y1));
			tp.setX2(Double.parseDouble(x2));
			tp.setY2(Double.parseDouble(y2));

			tp.setThumbType(ThumbType.NO_COMPRESS_CAIJIAN); // 不压缩直接裁剪
			isCompress = true;
		}

		try {

			// 上传头像图片
			for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = (String) it.next();
				MultipartFile fileIn = multipartRequest.getFile(key);

				attach = this.fileUplodService.uploadOneAttachmentToMongoOnlyCj(fileIn,
						multipartRequest, isCompress, tp);
			}

			// 更新头像图片
			String headImgAttachId = attach.get_id_m();
			DBObject updateResult = this.userService.updateHeadImage(_id, headImgAttachId);

			// 更新session级的头像缓存
			HttpSession session = request.getSession(true);
			session.setAttribute("user_head_img", headImgAttachId);

			result.put("success", "y");
			result.put("attach_id", headImgAttachId);

			logger.debug("上传文件完毕，上传结果\n{}", attach);
		} catch (Exception e) {
			return this.handleException(e);
		}

		return result;
	}

}