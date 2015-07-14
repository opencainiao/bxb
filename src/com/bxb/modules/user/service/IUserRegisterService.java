package com.bxb.modules.user.service;

import com.bxb.modules.user.model.User;

public interface IUserRegisterService {

	/****
	 * 注册一个用户
	 * 
	 * @param registeruser
	 * @return
	 */
	public String registWithEmail(User registeruser);

	/****
	 * 激活一个用户
	 * 
	 * @param email
	 * @param activecode
	 * @return
	 */
	public String active(String email, String activecode);
}
