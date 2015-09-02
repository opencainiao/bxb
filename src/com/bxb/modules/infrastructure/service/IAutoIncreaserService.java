package com.bxb.modules.infrastructure.service;

public interface IAutoIncreaserService {

	/****
	 * 获取一个标示主体的自增值（整数）
	 * 
	 * @param mainkey
	 * @return
	 */
	public int getAutoIncreasedInteger(String mainkey);

	/****
	 * 获取一个标示主体的自增值（字符串）
	 * 
	 * @param mainkey
	 * @return
	 */
	public String getAutoIncreaseString(String mainkey);
}
