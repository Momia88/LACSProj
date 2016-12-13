package com.hpbu.lacs.model;

import java.util.HashMap;
import java.util.List;

public class LogResultObj {
	List<HashMap<String, String>> titleList;
	List<HashMap<String, String>> recordList;
	HashMap<String, String> commDescriptionPair;
	HashMap<String, String> commResultPair;

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

	public HashMap<String, String> getCommDescriptionPair() {
		return commDescriptionPair;
	}

	public void setCommDescriptionPair(HashMap<String, String> commDescriptionPair) {
		this.commDescriptionPair = commDescriptionPair;
	}

	public HashMap<String, String> getCommResultPair() {
		return commResultPair;
	}

	public void setCommResultPair(HashMap<String, String> commResultPair) {
		this.commResultPair = commResultPair;
	}


}
