package com.bxb.common.globalobj.exception;

public class ValidateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidateException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
