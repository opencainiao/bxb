package com.bxb.modules.infrastructure.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxb.common.globalobj.RequestResult;
import com.bxb.common.util.MenuUtil;
import com.bxb.common.util.RequestUtil;
import com.bxb.common.util.ZTreeUtil;
import com.bxb.modules.base.BaseController;
import com.bxb.modules.infrastructure.model.SysMenu;
import com.bxb.modules.infrastructure.service.ISysMenuService;
import com.mongodb.util.JSON;

/****
 * 菜单管理
 * 
 * @author NBQ
 * @date 2012-4-19
 */
@Controller
@RequestMapping("/backend/menu")
@SuppressWarnings("rawtypes")
public class MenuManageController extends BaseController {

	@Autowired(required = true)
	@Qualifier("sysMenuService")
	private ISysMenuService sysMenuService;

	private static final Logger logger = LogManager
			.getLogger(MenuManageController.class);

	/****
	 * 菜单管理入口--进入菜单管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toManage")
	public ModelAndView manage(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("admin/infrastructure/menu/menu_manage");
	}

	/****
	 * 编辑菜单入口--进入菜单编辑页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toEdit")
	public ModelAndView toEdit(String menu_code, HttpServletRequest request,
			HttpServletResponse response) {

		SysMenu menuInf = this.sysMenuService.findMenuInf(menu_code);

		logger.debug(JSON.serialize(menuInf));

		request.setAttribute("menu", menuInf);

		return new ModelAndView("admin/infrastructure/menu/menu_edit");
	}

	/****
	 * 添加菜单入口--进入菜单添加页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("-- into add page --");

		Map dataIn = RequestUtil.ToLowerMap(request);

		int lvl = Integer.parseInt(dataIn.get("supmnulvl").toString()) + 1;
		request.setAttribute("SUPMNUCOD", dataIn.get("supmnucod"));
		request.setAttribute("SUPMNUCODNAM", dataIn.get("supmnunam"));
		request.setAttribute("THISMNULVL", lvl);

		return new ModelAndView("admin/infrastructure/menu/menu_add");
	}

	/****
	 * 菜单详细信息入口--查询菜单信息，并进入菜单信息页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toDetail")
	public ModelAndView toDetail(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("-- into detail page --");

		ModelAndView successM_V = new ModelAndView(
				"admin/infrastructure/menu/menu_detail");

		Map dataIn = RequestUtil.ToLowerMap(request);

		String menuCode = (String) dataIn.get("menu_code");
		if (request.getAttribute("menu_code") != null) {
			menuCode = (String) request.getAttribute("menu_code");
		}

		logger.debug(menuCode);

		SysMenu menuInf = null;
		SysMenu parentMenuInf = null;
		// 1. 查询该菜单信息及父菜单信息
		if (menuCode.equals("ROOT")) {
			menuInf = MenuUtil.createRootMenu();
		} else {
			menuInf = this.sysMenuService.findMenuInf(menuCode);
			if (menuInf.getSup_menu_code().equals("ROOT")) {
				parentMenuInf = MenuUtil.createRootMenu();
			} else {
				parentMenuInf = this.sysMenuService.findMenuInf(menuInf
						.getSup_menu_code());
			}

			request.setAttribute("parentMenu", parentMenuInf);
		}

		if (menuInf == null) {
			return successM_V;
		}
		// 2. 查询动作或子菜单
		if (!menuInf.isLeaf()) {
			// 有子菜单，查询下一级子菜单
			List<SysMenu> menuChildrens = this.sysMenuService
					.findChildren(menuCode);
			request.setAttribute("menuchildren", menuChildrens);
		}

		request.setAttribute("menu", menuInf);

		return successM_V;
	}

	/****
	 * 查询菜单的子菜单列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/findSubMnus")
	@ResponseBody
	public Object findSubMnus(HttpServletRequest request,
			HttpServletResponse response, String menu_code) {

		List<SysMenu> children = this.sysMenuService.findChildren(menu_code);

		if (children != null && !children.isEmpty()) {
			request.setAttribute("hasmenuchildren", true);
		}

		return JSON.serialize(children);
	}

	/****
	 * 添加菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/create")
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.debug("-- into create page --");
		Map dataIn = RequestUtil.ToLowerMap(request);

		this.sysMenuService.insert(dataIn);
		// 进入父节点信息页面 ，同时刷新菜单
		request.setAttribute("menu_code", dataIn.get("supmnucod"));
		request.setAttribute("refreshTree", "true");

		return this.toDetail(request, response);
	}

	/****
	 * 保存菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("-- into save page --");
		Map dataIn = RequestUtil.ToLowerMap(request);

		this.sysMenuService.update(dataIn);

		return this.toDetail(request, response);
	}

	@RequestMapping("/upMenu")
	public ModelAndView upMenu(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("-- into create page --");
		Map dataIn = RequestUtil.ToLowerMap(request);

		this.sysMenuService.moveMenu(dataIn, true);

		// 进入父节点信息页面 ，同时刷新菜单
		request.setAttribute("menu_code", dataIn.get("supmnucod"));
		request.setAttribute("refreshTree", "true");

		return this.toDetail(request, response);
	}

	@RequestMapping("/downMenu")
	public ModelAndView downMenu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("-- into create page --");
		Map dataIn = RequestUtil.ToLowerMap(request);

		this.sysMenuService.moveMenu(dataIn, false);

		// 进入父节点信息页面 ，同时刷新菜单
		request.setAttribute("menu_code", dataIn.get("supmnucod"));
		request.setAttribute("refreshTree", "true");

		return this.toDetail(request, response);
	}

	/****
	 * 删除菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delMnu")
	public ModelAndView delMenu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map dataIn = RequestUtil.ToLowerMap(request);

		this.sysMenuService.delMenu(dataIn);
		// 进入父节点信息页面 ，同时刷新菜单
		request.setAttribute("menu_code", dataIn.get("supmnucod"));
		request.setAttribute("refreshTree", "true");

		return this.toDetail(request, response);
	}

	/****
	 * 取所有菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/loadallmenu")
	@ResponseBody
	public Object menu(HttpServletRequest request, HttpServletResponse response) {

		SysMenu sysmnu = sysMenuService.getRootMenuTree();

		return ZTreeUtil.transToZtreeMnuList(sysmnu);
	}

	/****
	 * <pre>
	 * <li><a> <i class="fa fa-home"></i> 
	 * 				Home 
	 * 			    <span class="fa fa-chevron-down"></span>
	 * 			</a>
	 * 			<ul class="nav child_menu" style="display: none">
	 * 				<li><a href="index.html">Dashboard</a></li>
	 * 				<li><a href="index2.html">Dashboard2</a></li>
	 * 				<li><a href="index3.html">Dashboard3</a></li>
	 * 			</ul>
	 * 		</li>
	 * </pre>
	 * 
	 * @return
	 */
	@RequestMapping("/load_sys_menu_admin_home")
	@ResponseBody
	public Object sysAdminMenuHomePage(HttpServletRequest request,
			HttpServletResponse response) {

		RequestResult rr = new RequestResult();

		SysMenu root = MenuUtil.createRootMenu();

		try {
			List<SysMenu> sysmnus = sysMenuService.findMenuTreeBySupMnuCod(root
					.getMenu_code());

			String result = transToMenuStrAdminHome(sysmnus);
			rr.setSuccess(true);
			rr.setMessage(result);
		} catch (Exception e) {
			return this.handleException(e);
		}

		return rr;
	}

	/****
	 * <pre>
	 * <li>
	 *     <a> 
	 * 		  <i class="fa fa-home"></i> 
	 *  			  Home 
	 *  		      <span class="fa fa-chevron-down"></span>
	 *     	   </a>
	 * 		   <ul class="nav child_menu" style="display: none">
	 *  			  <li><a href="index.html">Dashboard</a></li>
	 *  			  <li><a href="index2.html">Dashboard2</a></li>
	 *  			  <li><a href="index3.html">Dashboard3</a></li>
	 *  		   </ul>
	 *    	   </li>
	 * </pre>
	 * 
	 * @param sysmnus
	 * @return
	 */
	private String transToMenuStrAdminHome(List<SysMenu> sysmnus) {

		if (sysmnus == null || sysmnus.isEmpty()) {
			return "";
		}

		StringBuffer sb = new StringBuffer();

		for (SysMenu sysmnu : sysmnus) {

			sb.append("<li><a> <i class=\"" + sysmnu.getIclass() + "\"></i>");
			sb.append(sysmnu.getMenu_name());
			if (!sysmnu.isLeaf()) {
				sb.append("<span class=\"fa fa-chevron-down\"></span>");
			}
			sb.append("</a>");

			if (!sysmnu.isLeaf()) {
				sb.append("<ul class=\"nav child_menu\" style=\"display: none\">");

				List<SysMenu> children_this = sysmnu.getChild_menu_List();

				for (SysMenu menu_tmp : children_this) {
					sb.append("<li><a data-link=\"" + menu_tmp.getPath() + "\">"
							+ menu_tmp.getMenu_name() + "</a></li>");
					
					
				}

				sb.append("</ul>");
			}

			sb.append("</li>");
		}

		return sb.toString();
	}
}
