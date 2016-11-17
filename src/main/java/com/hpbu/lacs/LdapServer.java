package com.hpbu.lacs;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.coretronic.util.sso.LDAPService;
import com.google.gson.Gson;

@Path("/v1/auth")
public class LdapServer {
	@GET
	@Path("/{userId}/{userpassword}")
	@Produces("application/json")
	public String getJson(@PathParam("userId") String userId, @PathParam("userpassword") String userpassword)
			throws UnsupportedEncodingException {
		LDAPService ldapService = new LDAPService(userId + "@Coretronic.com", userpassword);
		com.coretronic.util.sso.UserInfoBean bean = ldapService.getUserInfo(userId);
		LdapBean lb = new LdapBean();
		String auth;
		if (bean == null) {
			auth = "N";
		} else {
			auth = "Y";
		}
		return auth;
	}

	@GET
	@Path("/sayHello")
	public String sayHello() {
		System.out.println("sayHello");
		return "say hello !!";
	}
}
