package com.bxb.common.globalobj;

import java.util.Map;

public class ValidResult {

	private Map<String, String> errors;

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public boolean hasErrors() {
		if (this.errors == null || this.errors.isEmpty()) {
			return false;
		}

		return true;
	}

}
