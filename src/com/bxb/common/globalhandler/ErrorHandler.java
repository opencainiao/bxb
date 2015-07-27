package com.bxb.common.globalhandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.bxb.common.globalobj.RequestResult;
import com.bxb.common.globalobj.ValidResult;

public class ErrorHandler {

	public static Map<String, String> getErrorInfFromBindingResult(
			BindingResult br) {
		Map<String, String> errorNew = new HashMap<String, String>();

		List<FieldError> errors = br.getFieldErrors();
		for (FieldError error : errors) {
			errorNew.put(error.getField(), error.getDefaultMessage());
		}

		return errorNew;
	}

	public static RequestResult getRequestResultFromBindingResult(
			BindingResult br) {

		Map<String, String> errors = getErrorInfFromBindingResult(br);

		RequestResult rr = new RequestResult();
		rr.setBrErrors(errors);

		return rr;
	}

	public static RequestResult getRequestResultFromValidResult(
			ValidResult validResult) {

		Map<String, String> errors = validResult.getErrors();

		RequestResult rr = new RequestResult();
		rr.setBrErrors(errors);

		return rr;
	}

}
