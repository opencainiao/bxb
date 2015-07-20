package com.bxb.common.util.propertyeditor;

import java.beans.PropertyEditorSupport;

public class CustomerIntegerEditor extends PropertyEditorSupport {
	
	public String getAsText() {
		Double d = (Double) getValue();
		return d.toString();
	}

	public void setAsText(String str) {
		if (str == null || str.trim().equals(""))
			setValue(0);
		else
			setValue(Integer.parseInt(str));
	}
}