package com.example.ltn_admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ltn_admin.request.UserLoginRequest;
import com.example.ltn_admin.response.ApiResponse;
import com.example.ltn_admin.response.UserLoginResponse;
import com.example.ltn_admin.service.AccoutService;

import lombok.Builder;

@RestController
@CrossOrigin
@RequestMapping("api")
public class AccountController {
	
	@Autowired
	private AccoutService accoutService;
	
	@PostMapping("/login")
	public ApiResponse<UserLoginResponse> checkLogin(@RequestBody UserLoginRequest userLoginRequest){
		UserLoginResponse userLoginResponse = accoutService.checkLogin(userLoginRequest);
		ApiResponse<UserLoginResponse> apiResponse;
		
		if(userLoginResponse != null) {
			apiResponse = ApiResponse.<UserLoginResponse>builder()
					.success(true)
					.code(200)
					.message("success")
					.result(userLoginResponse)
					.build();
		} else {
			apiResponse = ApiResponse.<UserLoginResponse>builder()
					.success(false)
					.code(404)
					.message("fail")
					.result(null)
					.build();
		}
		return apiResponse;
	}
}
