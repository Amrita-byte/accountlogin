package com.ibm.msreskill.accountloginms.model;

import javax.annotation.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JWTReqwUserAccountInfo {	
	
	private static final long serialVersionUID = 5926468583005150707L;
	
	private String username;
	private String password;
	
	@Autowired
	private AccountInfo accountInfo;
	
	
	public JWTReqwUserAccountInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JWTReqwUserAccountInfo(String username, String password, AccountInfo accountInfo) {
		super();
		
		this.username = username;
		this.password = password;
		this.accountInfo = accountInfo;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}
	
	
	
}
