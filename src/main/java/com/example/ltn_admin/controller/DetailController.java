package com.example.ltn_admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ltn_admin.entity.Token;
import com.example.ltn_admin.response.ApiResponse;
import com.example.ltn_admin.service.DetailService;
import com.example.ltn_admin.service.TokenService;

@CrossOrigin
@RestController
@RequestMapping("api")
public class DetailController {

	@Autowired
	private DetailService detailService;
	
	@Autowired
	private TokenService tokenService;
	
//	@PostMapping("/payment/{licensePlate}")
//	public ApiResponse<Boolean> payment(@PathVariable("licensePlate") String licensePlate){
//		boolean result = detailService.updateEtcBalance(licensePlate);
//		ApiResponse<Boolean> apiResponse = ApiResponse.<Boolean>builder()
//				.success(true)
//				.code(200)
//				.result(result)
//				.message("success")
//				.build();
//		return apiResponse;
//	}
	
	@GetMapping("/token/{detailId}")
	public ApiResponse<List<Token>> payment(@PathVariable("detailId") int detailId){
		List<Token> tokens = tokenService.findTokenByDetailId(detailId);
		ApiResponse<List<Token>> apiResponse = ApiResponse.<List<Token>>builder()
				.success(true)
				.code(200)
				.result(tokens)
				.message("success")
				.build();
		return apiResponse;
	}
}
