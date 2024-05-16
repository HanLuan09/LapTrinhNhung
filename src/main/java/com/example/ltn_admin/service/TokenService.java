package com.example.ltn_admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ltn_admin.entity.Token;
import com.example.ltn_admin.repository.TokenRepository;

@Service
public class TokenService {
	
	@Autowired
	private TokenRepository tokenRepository;
	
	public List<Token> findTokenByDetailId(int detailId){
		List<Token> tokens = tokenRepository.findTokenByDetailId(detailId);
		if(tokens.isEmpty()) {
			throw(new RuntimeException("Not Found"));
		}
		return tokens;
	}
}
