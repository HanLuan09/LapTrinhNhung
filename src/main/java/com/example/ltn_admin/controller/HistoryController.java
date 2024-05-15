package com.example.ltn_admin.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ltn_admin.entity.History;
import com.example.ltn_admin.request.DateSearchRequest;
import com.example.ltn_admin.response.ApiResponse;
import com.example.ltn_admin.service.HistoryService;

@CrossOrigin
@RestController
@RequestMapping("api")
public class HistoryController {
	
	@Autowired
	private HistoryService historyService;
	
	@GetMapping("/history")
	public ApiResponse<List<History>> getAllHistories(){
		List<History> histories = historyService.findAllHistories();
		ApiResponse<List<History>> apiResponse = ApiResponse.<List<History>>builder()
				.success(true)
				.code(200)
				.result(histories)
				.message("success")
				.build();
		return apiResponse;
	}
	
	@GetMapping("/history/user/{idDetail}")
	public ApiResponse<List<History>> getAllHistoriesByUser(@PathVariable("idDetail") int idDetail){
		List<History> histories = historyService.findAllHistoriesByUser(idDetail);
		ApiResponse<List<History>> apiResponse = ApiResponse.<List<History>>builder()
				.success(true)
				.code(200)
				.result(histories)
				.message("success")
				.build();
		return apiResponse;
	}
	
	@PostMapping("/history/date")
	public ApiResponse<List<History>> getAllHistoriesByDate(@RequestBody DateSearchRequest dateSearchRequest) throws ParseException{
		List<History> histories = historyService.findAllHistoriesByDate(dateSearchRequest);
		ApiResponse<List<History>> apiResponse = ApiResponse.<List<History>>builder()
				.success(true)
				.code(200)
				.result(histories)
				.message("success")
				.build();
		return apiResponse;
	}
	
	@GetMapping("/history/user/date/{idDetail}")
	public ApiResponse<List<History>> getAllHistoriesByUserAndDate(@PathVariable("idDetail") int idDetail, @RequestBody DateSearchRequest dateSearchRequest) throws ParseException{
		List<History> histories = historyService.findAllHistoriesByUserAndDate(idDetail, dateSearchRequest);
		ApiResponse<List<History>> apiResponse = ApiResponse.<List<History>>builder()
				.success(true)
				.code(200)
				.result(histories)
				.message("success")
				.build();
		return apiResponse;
	}
	
	@PostMapping("/history/add/{idDetail}")
	public ApiResponse<Boolean> addHistory(@PathVariable("idDetail") int idDetail) throws ParseException{
		historyService.addHistory(idDetail);
		ApiResponse<Boolean> apiResponse = ApiResponse.<Boolean>builder()
				.success(true)
				.code(200)
				.result(true)
				.message("success")
				.build();
		return apiResponse;
	}
}
