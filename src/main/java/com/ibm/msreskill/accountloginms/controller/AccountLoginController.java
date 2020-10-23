package com.ibm.msreskill.accountloginms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.msreskill.accountloginms.config.JwtTokenUtil;
import com.ibm.msreskill.accountloginms.model.AccountInfo;
import com.ibm.msreskill.accountloginms.model.JWTReqwUserAccountInfo;
import com.ibm.msreskill.accountloginms.model.JwtReswToken;
import com.ibm.msreskill.accountloginms.model.UserInfo;
import com.ibm.msreskill.accountloginms.service.AccountLoginService;
import com.ibm.msreskill.accountloginms.service.JwtUserDetailsService;


@RestController
public class AccountLoginController {
	
	@Autowired
	AccountLoginService accountLoginService;
	
	@Autowired
	JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	public AccountLoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GetMapping(path = "/welcome")
	public String geWelcome() {		
		return "Welcome to Grocery Store" ;
	}
	
	@GetMapping(path = "/register/{username}/{password}")
	public JWTReqwUserAccountInfo register(@PathVariable String username,@PathVariable String password) {
		return accountLoginService.register(username, password);
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public JWTReqwUserAccountInfo registerNew(@RequestBody UserInfo userInfo) {
		return accountLoginService.register(userInfo.getUsername(), userInfo.getPassword());
	}
		
	@GetMapping(path = "/getUserList")
	public List<UserInfo> getUserList() {
		return accountLoginService.getUserList();
	}
	
	@GetMapping(path = "/getAccountList")
	public List<AccountInfo> getAccountList() {
		return accountLoginService.getAccountList();
	}
	
	@GetMapping(path = "/getAccountDetails/{accountId}")
	public AccountInfo getAccountDetails(@PathVariable String accountId) {
		return accountLoginService.getAccountDetails(accountId);
	}
	
	@GetMapping(path = "/getUserDetails/{username}")
	public UserInfo getUserDetails(@PathVariable String username) {
		return accountLoginService.getUserDetails(username);
	}
	
	@GetMapping(path = "/getUserAccountDetails/{username}")
	public JWTReqwUserAccountInfo getUserAccountDetails(@PathVariable String username) {
		return accountLoginService.getUserAccountDetails(username);
	}
	
	@RequestMapping(value = "/getAuthenticationToken", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTReqwUserAccountInfo jwtReqwUserAccountInfo) 
			throws Exception {
		 
		accountLoginService.authenticate(jwtReqwUserAccountInfo.getUsername(),jwtReqwUserAccountInfo.getPassword());
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtReqwUserAccountInfo.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtReswToken(token));
	}
	
	
	@RequestMapping(value = "/authenticateTest",method = RequestMethod.POST)
	public JWTReqwUserAccountInfo authenticateTest(@RequestBody JWTReqwUserAccountInfo userAccountInfo) {
		return accountLoginService.authenticateTest(userAccountInfo);
	}

}
