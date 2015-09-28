package com.bxb.modules.global.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bxb.common.globalobj.WorkbookConfig;
import com.bxb.common.util.ExportUtils;
import com.bxb.common.util.HttpServletRequestUtil;
import com.bxb.common.util.WebContextUtil;
import com.bxb.modules.base.BaseController;

/****
 * 通用Excel导出控制器
 * 
 * @author NBQ
 */
@Controller
@RequestMapping("/download_excel")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DownLoadExcelController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(DownLoadExcelController.class);

	/****
	 * 下载整表,单sheet文件
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/single", method = RequestMethod.POST)
	public void resetpasswordsuccess(Model model, HttpServletRequest request,
			HttpServletResponse response, String titles, String fields,
			String fileName, String beanName, String methodName) {

		logger.debug("下载单sheet文件");
		HttpServletRequestUtil.debugParams(request);

		try {
			// 获取类
			Object o = WebContextUtil.getBean(beanName);
			Class clazz = o.getClass();
			Method m = clazz.getDeclaredMethod(methodName);
			List list = (List) m.invoke(o);

			// titles = new String(titles.getBytes("ISO-8859-1"), "UTF-8");

			String[] titleNames = titles.split(",");
			String[] fieldNames = fields.split(",");
			String sheetName = "sheet0";

			WorkbookConfig wcg = new WorkbookConfig();
			wcg.setWorkbookName(fileName);
			wcg.addSheetName(sheetName);
			wcg.addSheetField(sheetName, fieldNames);
			wcg.addSheetTitle(sheetName, titleNames);

			HSSFWorkbook wb = ExportUtils
					.createSingSheetHSSFWorkbook(wcg, list);

			ExportUtils.setHeader(response, fileName);
			// 获取输出流，写入excel 并关闭
			ServletOutputStream out = response.getOutputStream();

			wb.write(out);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
