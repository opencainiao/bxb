package com.bxb.modules.base;

import com.bxb.modules.user.model.User;
import com.mongodb.DBObject;

public interface IBaseService {

	/****
	 * 判断是否合法的objectId
	 * 
	 * @param _id
	 * @return
	 */
	public boolean isValidObjId(String _id);

	/****
	 * 取当前登陆用户
	 * 
	 * @return
	 */
	public User getUser();

	/****
	 * 取当前登陆用户id
	 * 
	 * @return
	 */
	public String getUserId();

	/****
	 * 取当前登陆用户姓名
	 * 
	 * @return
	 */
	public String getUsername();

	/****
	 * 设置对象创建信息
	 * 
	 * @param model
	 */
	public void setCreateInfo(BaseModel model);

	/****
	 * 设置对象创建信息
	 * 
	 * @param model
	 */
	public void setCreateInfo(BaseModel model, String time);

	/****
	 * 设置对象的修改信息
	 * 
	 * @param dbObject
	 */
	public void setModifyInfo(DBObject dbObject);

	/****
	 * 设置更新信息
	 */
	public void setModifyInfoWithUserId(DBObject dbObject, String user_id);

	/****
	 * 设置对象创建信息
	 * 
	 * @param model
	 */
	public void setCreateInfoWithUserId(BaseModel model, String user_id);

	/****
	 * 设置对象创建信息
	 * 
	 * @param model
	 */
	public void setCreateInfoWithUserId(BaseModel model, String time,
			String user_id);
}
