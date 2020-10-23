package com.ibm.msreskill.accountloginms.model;

import java.io.Serializable;

public class JwtReswToken implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtReswToken(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}