package com.example.ltn_admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ltn_admin.entity.History;
import com.example.ltn_admin.repository.HistoryRepository;

@Service
public class HistoryService {
	
	@Autowired
	private HistoryRepository historyRepository;
	
	public List<History> findAllHistories() {
		List<History> histories = historyRepository.findAllHistories();
		return histories;
	}
	
	public List<History> findAllHistoriesByUser(int idDetail) {
		List<History> histories = historyRepository.findAllHistoriesByUser(idDetail);
		if(histories.isEmpty()) {
			throw(new RuntimeException("Not Found"));
		}
		return histories;
	}
}
