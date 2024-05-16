package com.example.ltn_admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ltn_admin.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>{
	
	@Query(value = "SELECT * FROM token t WHERE t.detail_id = :detailId", nativeQuery = true)
	List<Token> findTokenByDetailId(@Param("detailId") int detailId);
}
