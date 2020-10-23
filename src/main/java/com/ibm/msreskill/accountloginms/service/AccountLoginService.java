package com.ibm.msreskill.accountloginms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.msreskill.accountloginms.model.AccountInfo;
import com.ibm.msreskill.accountloginms.model.JWTReqwUserAccountInfo;
import com.ibm.msreskill.accountloginms.model.UserInfo;
import com.ibm.msreskill.accountloginms.repo.AccountRepository;
import com.ibm.msreskill.accountloginms.repo.UserRepository;

@Service
public class AccountLoginService {
	
	@Autowired
	JWTReqwUserAccountInfo userAccountInfo;
	
	@Autowired
	AccountInfo accountInfo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	UserInfo userInfo;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	
	public JWTReqwUserAccountInfo register(String username,String password) {
		
		String accountId = java.util.UUID.randomUUID().toString();
		if (!username.equals("admin")) {
			accountInfo.setAccountType("user");
			accountInfo.setAccountId(accountId);
		}else {
			accountInfo.setAccountType("admin");
			accountInfo.setAccountId("101");
		}
		// save the account object in db
		accountRepo.save(accountInfo);
		
		// save the user object in db
		userInfo = new UserInfo(username,
				encoder().encode(password),
				accountInfo.getAccountId());
		userRepo.save(userInfo);
		
		// set the userAccount object properties
		userAccountInfo.setAccountInfo(accountInfo);
		userAccountInfo.setPassword(userInfo.getPassword());
		userAccountInfo.setUsername(userInfo.getUsername());
		
		return userAccountInfo;
	}

	public List<UserInfo> getUserList() {
		// TODO Auto-generated method stub
		return userRepo.findAll();		
	}
	
	public List<AccountInfo> getAccountList() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();		
	}
	
	public AccountInfo getAccountDetails(String accountId) {
		// TODO Auto-generated method stub
		return accountRepo.findById(accountId).get();		
	}
	
	public UserInfo getUserDetails(String username) {
		// TODO Auto-generated method stub
		return userRepo.findById(username).get();		
	}

	public JWTReqwUserAccountInfo getUserAccountDetails(String username) {
		// TODO Auto-generated method stub		
		userAccountInfo.
		setAccountInfo(accountRepo.findById(userRepo.findById(username).get().getAccountId()).get());
		userAccountInfo.setUsername(userRepo.findById(username).get().getUsername());
		userAccountInfo.setPassword("*********");
		return userAccountInfo;
	}
	
	

	public void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub

		try {		
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}	
		
	}

	public JWTReqwUserAccountInfo authenticateTest(JWTReqwUserAccountInfo userAccountInfo) {
		// TODO Auto-generated method stub
		userInfo = userRepo.findById(userAccountInfo.getUsername()).get();
		
		System.out.println("userInfo.getUsername()" + userInfo.getUsername());
		System.out.println("userInfo.getPassword()" + userInfo.getPassword());		
		
		if(encoder().matches(userAccountInfo.getPassword(), userInfo.getPassword())) {		
		userAccountInfo.setPassword(userInfo.getPassword());
		accountInfo = accountRepo.findById(userInfo.getAccountId()).get();
		userAccountInfo.setAccountInfo(accountInfo);
		}
		return userAccountInfo;
	}
	
	 @Bean
	 public PasswordEncoder encoder() {
	        return new BCryptPasswordEncoder();
	 }

}
