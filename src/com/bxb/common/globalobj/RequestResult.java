package com.bxb.common.globalobj;

import java.util.List;
import java.util.Map;

import org.mou.common.JsonUtil;

@SuppressWarnings("rawtypes")
public class RequestResult {

	private String success; // y-成功,n-失败
	private String message; // 消息
	private Map<String, String> errors;
	private Map<String, String> brErrors;
	private Object object;// 业务对象
	private List objects; // 一组业务对象

	public String getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		if (success) {
			this.success = "y";
		} else {
			this.success = "n";
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
		this.success = "n";
	}

	public Map<String, String> getBrErrors() {
		return brErrors;
	}

	public void setBrErrors(Map<String, String> brErrors) {
		this.brErrors = brErrors;
		this.success = "n";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public List getObjects() {
		return objects;
	}

	public void setObjects(List objects) {
		this.objects = objects;
	}

	@Override
	public String toString() {
		return JsonUtil.toJsonStr(this);
	}
}
