package com.bxb.modules.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.mou.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.bxb.common.cash.context.Contexkeys;
import com.bxb.common.cash.context.ThreadContextManager;
import com.bxb.common.globalobj.ValidResult;
import com.bxb.common.util.ValidateUtil;
import com.bxb.modules.user.model.User;
import com.mongodb.DBObject;

public class BaseService implements IBaseService {

	@Autowired
	private Validator validator;

	/****
	 * 基于JSR301校验对象<br>
	 * 
	 * 样例：<br>
	 * 
	 * <PRE>
	 * ValidResult validResult = this.validate(XXX);
	 * if (validResult.hasErrors()) {
	 * 	return ErrorHandler.getRequestResultFromValidResult(validResult);
	 * }
	 * </PRE>
	 * 
	 * @param source
	 * @return
	 */
	public ValidResult validate(Object source) {

		ValidResult validresult = new ValidResult();

		Set<ConstraintViolation<Object>> violations = validator
				.validate(source);

		if (violations != null && violations.size() > 0) {

			Map<String, String> errors = new HashMap<String, String>();

			for (ConstraintViolation<Object> violation : violations) {
				// 这部分当然是获取验证的出错的字段名
				String field = violation.getPropertyPath().toString();
				String message = violation.getMessage();

				errors.put(field, message);
			}

			validresult.setErrors(errors);
		}

		return validresult;
	}

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
