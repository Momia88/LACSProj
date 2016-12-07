package com.hpbu.lacs.model;

import java.util.HashMap;
import java.util.List;

public class LogResultObj {
	List<HashMap<String,String>> titleList;
	List<HashMap<String,String>> recordList;
	
	public List<HashMap<String, String>> getTitleList() {
		return titleList;
	}
	public void setTitleList(List<HashMap<String, String>> titleList) {
		this.titleList = titleList;
	}
	public List<HashMap<String, String>> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<HashMap<String, String>> recordList) {
		this.recordList = recordList;
	}
}
