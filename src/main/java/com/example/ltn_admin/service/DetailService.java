package com.example.ltn_admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ltn_admin.entity.Detail;
import com.example.ltn_admin.repository.DetailRepository;

@Service
public class DetailService {

	@Autowired
	private DetailRepository detailRepository;
	
	public Detail getDetailByLicensePlate(String licensePlate) {
		return detailRepository.findByLicensePlate(licensePlate)
				.orElseThrow(() -> new RuntimeException("Not Fount"));
	}
}
