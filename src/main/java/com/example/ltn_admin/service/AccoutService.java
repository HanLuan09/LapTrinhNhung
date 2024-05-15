package com.example.ltn_admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ltn_admin.entity.Account;
import com.example.ltn_admin.repository.AccountRepository;
import com.example.ltn_admin.request.UserLoginRequest;
import com.example.ltn_admin.response.UserLoginResponse;

@Service
public class AccoutService {

	@Autowired
	private AccountRepository accountRepository;
	
	public UserLoginResponse checkLogin(UserLoginRequest userLoginRequest) {
		Account account = accountRepository.findAccountByUsernameAndPassword(userLoginRequest.getUsername(), userLoginRequest.getPassword());
		
		if(account != null) {
			UserLoginResponse userLoginResponse = new UserLoginResponse();
			if(account.getDetail() != null) {
				userLoginResponse.setDetailId(account.getDetail().getId());
			}
			userLoginResponse.setUsername(account.getUserName());
			userLoginResponse.setRole(account.getRole());
			
			return userLoginResponse;
		} else {
			return null;
		}
	}
}
