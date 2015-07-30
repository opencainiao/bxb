package com.bxb.modules.base;

import org.mou.common.DateUtil;

import com.bxb.common.cash.context.Contexkeys;
import com.bxb.common.cash.context.ThreadContextManager;
import com.bxb.common.util.ValidateUtil;
import com.bxb.modules.user.model.User;
import com.mongodb.DBObject;

public class BaseService implements IBaseService {

	/****
	 * 判断是否合法的objectId
	 * 
	 * @param _id
	 * @return
	 */
	public boolean isValidObjId(String _id) {
		return ValidateUtil.isValidObjId(_id);
	}

	/****
	 * 取当前登陆用户
	 * 
	 * @return
	 */
	public User getUser() {
		return (User) ThreadContextManager.get(Contexkeys.USER);

	}

	/****
	 * 取当前登陆用户id
	 * 
	 * @return
	 */
	public String getUserId() {
		return (String) ThreadContextManager.get(Contexkeys.USERID);

	}

	/****
	 * 取当前登陆用户姓名
	 * 
	 * @return
	 */
	public String getUsername() {
		return (String) ThreadContextManager.get(Contexkeys.USERNAME);
	}

	/****
	 * 设置对象创建信息
	 * 
	 * @param model
	 */
	public void setCreateInfo(BaseModel model) {
		String date = DateUtil.getCurdate();
		String time = DateUtil.getCurrentTimsmp();
		String userid = this.getUserId();
		String username = this.getUsername();

		model.setCuserid(userid);
		model.setCusername(username);
		model.setCtime(time);
		model.setCdate(date);
		model.setDelflg("0");// 未删除
	}

	/****
	 * 设置对象创建信息
	 * 
	 * @param model
	 */
	public void setCreateInfo(BaseModel model, String time) {
		String userid = this.getUserId();
		String username = this.getUsername();

		model.setCuserid(userid);
		model.setCusername(username);
		model.setCtime(time);
		model.setCdate(time.substring(0, 10));
		model.setDelflg("0");// 未删除
	}

	/****
	 * 设置更新信息
	 */
	@Override
	public void setModifyInfo(DBObject dbObject) {

		dbObject.put("last_op_user_id", this.getUserId());
		dbObject.put("last_op_user_name", this.getUsername());
		dbObject.put("last_op_date", DateUtil.getCurdate());
		dbObject.put("last_op_time", DateUtil.getCurrentTimsmp());
	}

	/****
	 * 设置更新信息
	 */
	@Override
	public void setModifyInfoWithUserId(DBObject dbObject, String user_id) {

		dbObject.put("last_op_user_id", user_id);
		dbObject.put("last_op_user_name", this.getUsername());
		dbObject.put("last_op_date", DateUtil.getCurdate());
		dbObject.put("last_op_time", DateUtil.getCurrentTimsmp());
	}

	/****
	 * 设置对象创建信息
	 * 
	 * @param model
	 */
	public void setCreateInfoWithUserId(BaseModel model, String user_id) {
		String date = DateUtil.getCurdate();
		String time = DateUtil.getCurrentTimsmp();
		String username = this.getUsername();

		model.setCuserid(user_id);
		model.setCusername(username);
		model.setCtime(time);
		model.setCdate(date);
		model.setDelflg("0");// 未删除
	}

	/****
	 * 设置对象创建信息
	 * 
	 * @param model
	 */
	public void setCreateInfoWithUserId(BaseModel model, String time,
			String user_id) {
		String username = this.getUsername();

		model.setCuserid(user_id);
		model.setCusername(username);
		model.setCtime(time);
		model.setCdate(time.substring(0, 10));
		model.setDelflg("0");// 未删除
	}

}
