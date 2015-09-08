package com.bxb.common.globalobj.exception;

import com.bxb.common.globalhandler.ErrorHandler;
import com.bxb.common.globalobj.RequestResult;
import com.bxb.common.globalobj.ValidResult;

public class ValidateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ValidResult validResult;

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(ValidResult validResult) {

		this.validResult = validResult;
	}

	public RequestResult getRequestResult() {
		if (this.validResult != null) {
			return ErrorHandler.getRequestResultFromValidResult(validResult);
		} else {
			RequestResult rr = new RequestResult();
			rr.setSuccess(false);
			rr.setMessage(this.getMessage());

			return rr;
		}
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
