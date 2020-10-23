package com.ibm.msreskill.accountloginms.model;

import javax.annotation.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class AccountInfo {
	
	@Id
	private String accountId;
	private String accountType;
	
	public AccountInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountInfo(String accountId, String accountType) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
	}
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}	
	
	
}
