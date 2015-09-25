package com.bxb.modules.global.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.DateUtil;
import org.mou.common.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bxb.common.util.HttpServletRequestUtil;
import com.bxb.common.util.ValidateUtil;
import com.bxb.modules.base.BaseController;
import com.bxb.modules.global.model.Attachment;
import com.bxb.modules.global.service.IAttachmentService;
import com.bxb.modules.global.service.ThumbParam;
import com.bxb.modules.global.service.ThumbType;
import com.mongodb.gridfs.GridFSDBFile;

/****
 * 全局文件上传控制器
 * 
 * @author sks
 *
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentUploadController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(AttachmentUploadController.class);

	@Resource(name = "attachmentService")
	private IAttachmentService attachmentService;

	/****
	 * 上传一个附件 上传附件时，默认对图片生成缩略图
	 * 
	 * @param request
	 * @return 返回生成的附件信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ajaxUploadOneAttachment1", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxUploadOneAttachment(HttpServletRequest request) {

		logger.debug("ajaxUploadOneAttachment");

		Map<String, Object> result = new HashMap<String, Object>();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		String dirpath = DateUtil.getCurdate();

		String iscompress = request.getParameter("iscompress");

		boolean isCompress = false;
		if (!StringUtil.isEmpty(iscompress) && iscompress.equals("1")) {
			isCompress = true;
		}

		Attachment attach = null;

		try {
			List<ThumbParam> tps = null;

			String tpsStr = request.getParameter("cp_param");
			if (StringUtil.isNotEmpty(tpsStr)) {
				tps = new ArrayList<ThumbParam>();

				String[] alltps = tpsStr.split(",");
				for (String tpstmp : alltps) {
					String[] tp_arr = tpstmp.split("x");

					ThumbParam tp = new ThumbParam();
					tp.setWidth(Integer.parseInt(tp_arr[0]));
					tp.setHeight(Integer.parseInt(tp_arr[1]));
					tp.setThumbType(tp_arr[2]);
					tps.add(tp);
				}
			}

			for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = (String) it.next();
				MultipartFile fileIn = multipartRequest.getFile(key);

				attach = this.attachmentService.uploadOneAttachmentToServerDisk(
						fileIn, multipartRequest, dirpath, isCompress, tps);
			}

			result.put("success", "y");
			result.put("attach", attach);
		} catch (Exception e) {
			return this.handleException(e);
		}

		return result;
	}

	/****
	 * 删除一个附件
	 * 
	 * @param imgurl
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ajaxDeleteOneAttachment", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxDeleteOneAttachment(String _id_m,
			HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtil.isEmpty(_id_m)) {
			result.put("success", "y");
			return result;
		}

		try {
			attachmentService.deleteOneAttachment(_id_m, request);
			result.put("success", "y");
		} catch (Exception e) {
			result.put("success", "n");
			result.put("message", StringUtil.getStackTrace(e));
		}
		return result;
	}

	/****
	 * 上传一个附件到mongo数据库。 上传附件时，默认不对图片生成缩略图
	 * 
	 * @param request
	 * @return 返回生成的附件信息
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxUploadOneAttachmentToMongo(HttpServletRequest request)
			throws UnsupportedEncodingException {

		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");// 你的编码格式
		}

		logger.debug("ajaxUploadOneAttachment");

		Map<String, Object> result = new HashMap<String, Object>();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		String iscompress = request.getParameter("iscompress");

		boolean isCompress = false;
		if (!StringUtil.isEmpty(iscompress) && iscompress.equals("1")) {
			isCompress = true;
		}

		Attachment attach = null;

		try {
			List<ThumbParam> tps = null;

			String tpsStr = request.getParameter("cp_param");
			if (StringUtil.isNotEmpty(tpsStr)) {
				tps = new ArrayList<ThumbParam>();

				String[] alltps = tpsStr.split(",");
				for (String tpstmp : alltps) {
					String[] tp_arr = tpstmp.split("x");

					ThumbParam tp = new ThumbParam();
					tp.setWidth(Integer.parseInt(tp_arr[0]));
					tp.setHeight(Integer.parseInt(tp_arr[1]));
					tp.setThumbType(tp_arr[2]);

					if (tp_arr.length > 3) {
						tp.setX1(Double.parseDouble(tp_arr[3]));
						tp.setY1(Double.parseDouble(tp_arr[4]));
						tp.setX2(Double.parseDouble(tp_arr[5]));
						tp.setY2(Double.parseDouble(tp_arr[6]));
					}

					tps.add(tp);
				}
			}

			for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = (String) it.next();
				MultipartFile fileIn = multipartRequest.getFile(key);

				attach = this.attachmentService.uploadOneAttachmentToMongo(
						fileIn, multipartRequest, isCompress, tps);
			}

			result.put("success", "y");
			result.put("attach", attach);

			logger.debug("上传文件完毕，上传结果\n{}", attach);
		} catch (Exception e) {
			return this.handleException(e);
		}

		return result;
	}

	/****
	 * 上传一个要裁剪的图片到mongo数据库。 只存储裁剪后的图片
	 * 
	 * @param request
	 * @return 返回生成的附件信息
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/upload_pic_cj", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxUploadOneAttachmentToMongoOnlyCJ(
			HttpServletRequest request, String x1, String y1, String x2,
			String y2, String w, String h) throws UnsupportedEncodingException {

		HttpServletRequestUtil.debugParams(request);
		logger.debug("upload_pic_cj");

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

			for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = (String) it.next();
				MultipartFile fileIn = multipartRequest.getFile(key);

				attach = this.attachmentService
						.uploadOneAttachmentToMongoOnlyCj(fileIn,
								multipartRequest, isCompress, tp);
			}

			result.put("success", "y");
			result.put("attach", attach);

			logger.debug("上传文件完毕，上传结果\n{}", attach);
		} catch (Exception e) {
			return this.handleException(e);
		}

		return result;
	}

	/****
	 * 查看文件(55fd2c08b0fa0219b46804a0)
	 * 
	 * @param request
	 * @param response
	 * @param _id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/{_id}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable String _id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Attachment att = this.attachmentService.getAttachMent(_id);

		if (att != null) {

			if (att.isImage()) {
				response.setContentType("image/gif");
			} else {
				// 文件下载
				String header = "attachment; filename=#FILENAME#";
				String fileName = att.getOriName() + "." + att.getSuffix();

				header = header.replace("#FILENAME#", fileName);
				response.setHeader("Content-Disposition", header);
				response.setContentType("application/octet-stream; charset=UTF-8");

				logger.debug("header[{}]", header);
			}

			// 查询真正的file
			String _idFile = att.getFile_id();
			GridFSDBFile gfsFile = this.attachmentService.getById(_idFile);

			logger.debug("_idFile[{}]", _idFile);

			if (gfsFile != null) {
				gfsFile.writeTo(response.getOutputStream());
			}
		}
	}
}
