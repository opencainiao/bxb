package com.bxb.testcontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bxb.modules.base.BaseController;

/****
 * 电话管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/test")
public class ShowImageController extends BaseController {

	/****
	 * 进入添加电话页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public String add(HttpServletRequest request,
			Model model) {

		// 开启modelDriven
		return "test/showImage";
	}

}