package com.hpbu.lacs;

import java.sql.Connection;
import java.util.ArrayList;


public class AccessManager {
	public ArrayList<ChromaObj> getDBData(String sqlStr) throws Exception {

		ArrayList<ChromaObj> courseList = new ArrayList<ChromaObj>();
		DBConnect dbConnect = new DBConnect();
		Connection con = dbConnect.getConn();
		DataParser access = new DataParser();
		courseList = access.getCourses(con, sqlStr);
		return courseList;
	}
}
