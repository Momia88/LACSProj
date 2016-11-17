package com.hpbu.lacs;

public class LdapBean {
	private String auth;
	private String cname;
	private String ename;
	private String company;
	private String email;
	private String empno;
	private String extension;
	private String dn;
	
	public String getAuth() {
		return auth;
	}
	public String getCname() {
		return cname;
	}
	public String getEname() {
		return ename;
	}
	public String getEmail() {
		return email;
	}
	public String getEmpno() {
		return empno;
	}
	public String getExtension() {
		return extension;
	}
	public String getDn() {
		return dn;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}	
}
