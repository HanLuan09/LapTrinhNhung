package com.example.ltn_admin.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ltn_admin.entity.Detail;
import com.example.ltn_admin.entity.History;
import com.example.ltn_admin.repository.HistoryRepository;
import com.example.ltn_admin.request.DateSearchRequest;

@Service
public class HistoryService {
	
	@Autowired
	private HistoryRepository historyRepository;
	
	public List<History> findAllHistories() {
		List<History> histories = historyRepository.findAllHistories();
		if(histories.isEmpty()) {
			throw(new RuntimeException("Not Found"));
		}
		return histories;
	}
	
	public List<History> findAllHistoriesByUser(int idDetail) {
		List<History> histories = historyRepository.findAllHistoriesByUser(idDetail);
		if(histories.isEmpty()) {
			throw(new RuntimeException("Not Found"));
		}
		return histories;
	}
	
	public List<History> findAllHistoriesByDate(DateSearchRequest dateSearchRequest) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date startDate = new java.sql.Date(sdf.parse(dateSearchRequest.getStartDate()).getTime());
		Date endDate = new java.sql.Date(sdf.parse(dateSearchRequest.getEndDate()).getTime());
		List<History> histories = historyRepository.findAllHistoriesByDate(startDate, endDate);
		return histories;
	}
	
	public List<History> findAllHistoriesByUserAndDate(int idDetail, DateSearchRequest dateSearchRequest) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date startDate = new java.sql.Date(sdf.parse(dateSearchRequest.getStartDate()).getTime());
		Date endDate = new java.sql.Date(sdf.parse(dateSearchRequest.getEndDate()).getTime());
		List<History> histories = historyRepository.findAllHistoriesByUserAndDate(idDetail, startDate, endDate);
		if(histories.isEmpty()) {
			throw(new RuntimeException("Not Found"));
		}
		return histories;
	}
	
	public void addHistory(int idDetail) {
		
		LocalDateTime date = LocalDateTime.now();
		History history = new History();
		history.setTollStationName("QL1-Hà Nội");
		history.setTollStationAddress("Hà Nội");
		history.setExpense("10000");
		history.setTime(date);
		history.setPaymentStatus("Paid");
		
		Detail detail = new Detail();
		detail.setId(idDetail);
		history.setDetail(detail);;
		
		historyRepository.save(history);		
	}
}
