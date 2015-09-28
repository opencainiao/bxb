package com.bxb.common.globalobj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkbookConfig {

	private String workbookName;
	private List<String> sheetNames;

	private Map<String, String[]> sheetTitles;
	private Map<String, String[]> sheetFields;

	public String getWorkbookName() {
		return workbookName;
	}

	public void setWorkbookName(String workbookName) {
		this.workbookName = workbookName;
	}

	public List<String> getSheetNames() {
		return sheetNames;
	}

	public void setSheetNames(List<String> sheetNames) {
		this.sheetNames = sheetNames;
	}

	public Map<String, String[]> getSheetTitles() {
		return sheetTitles;
	}

	public void setSheetTitles(Map<String, String[]> sheetTitles) {
		this.sheetTitles = sheetTitles;
	}

	public Map<String, String[]> getSheetFields() {
		return sheetFields;
	}

	public void setSheetFields(Map<String, String[]> sheetFields) {
		this.sheetFields = sheetFields;
	}

	public void addSheetName(String sheetName) {
		if (this.sheetNames == null) {
			this.sheetNames = new ArrayList<String>();
		}
		this.sheetNames.add(sheetName);
	}

	public void addSheetField(String sheetName, String[] Fields) {

		if (this.sheetFields == null) {
			this.sheetFields = new HashMap<String, String[]>();
		}
		this.sheetFields.put(sheetName, Fields);
	}

	public void addSheetTitle(String sheetName, String[] titles) {

		if (this.sheetTitles == null) {
			this.sheetTitles = new HashMap<String, String[]>();
		}
		this.sheetTitles.put(sheetName, titles);
	}
}
