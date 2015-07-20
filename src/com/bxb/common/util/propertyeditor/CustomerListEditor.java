package com.bxb.common.util.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.util.List;

public class CustomerListEditor extends PropertyEditorSupport {

	public String getAsText() {
		List d = (List) getValue();
		return d.toString();
	}

	public void setAsText(String str) {
		setValue(null);
	}
}