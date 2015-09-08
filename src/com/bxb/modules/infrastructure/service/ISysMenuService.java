package com.bxb.modules.infrastructure.service;

import java.util.List;
import java.util.Map;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.infrastructure.model.SysMenu;
import com.mongodb.DBObject;

@SuppressWarnings("rawtypes")
public interface ISysMenuService {

	public PageVO findBatch(Map map);

	/****
	 * 根据菜单码，读取菜单信息(不包含该菜单的子菜单)
	 * 
	 * @param mnucod
	 * @return
	 */
	public SysMenu findMenuInf(String mnucod);

	/****
	 * 根据菜单码，读取该菜单的子菜单
	 * 
	 * @param menuId
	 * @return
	 */
	public List<SysMenu> findChildren(String menuCod);

	/****
	 * 添加一个菜单
	 * 
	 * @param map
	 */
	public String insert(SysMenu sysMenu) throws Exception;

	/****
	 * 保存菜单
	 * 
	 * @param dataIn
	 * @return
	 */
	public DBObject update(SysMenu sysmenu);

	/****
	 * 移动菜单
	 * 
	 * @param dataIn
	 * @param isMoveUp
	 */
	public void moveMenu(Map dataIn, boolean isMoveUp);

	/****
	 * 删除菜单
	 * 
	 * @param menuId
	 * @param type
	 */
	public void delMenu(String _id);

	/****
	 * 读取系统菜单树
	 * 
	 * @return
	 */
	public SysMenu getRootMenuTree();

	/****
	 * 读取菜单树
	 * 
	 * @param supMnuCod
	 * @return
	 */
	public List<SysMenu> findMenuTreeBySupMnuCod(String supMnuCod);

}
