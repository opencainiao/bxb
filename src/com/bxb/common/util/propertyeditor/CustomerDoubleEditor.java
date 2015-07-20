package com.bxb.common.util.propertyeditor;

import java.beans.PropertyEditorSupport;

public class CustomerDoubleEditor extends PropertyEditorSupport {
	
	public String getAsText() {
		Double d = (Double) getValue();
		return d.toString();
	}

	public void setAsText(String str) {
		if (str == null || str.trim().equals(""))
			setValue(0.0d);
		else
			setValue(Double.parseDouble(str));
	}
}