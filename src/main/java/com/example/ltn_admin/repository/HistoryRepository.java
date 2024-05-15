package com.example.ltn_admin.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ltn_admin.entity.History;

public interface HistoryRepository extends JpaRepository<History, Integer>{
	
	@Query(value = "SELECT * FROM history h ORDER BY h.time DESC", nativeQuery = true)
	List<History> findAllHistories();
	
	@Query(value = "SELECT * FROM history h WHERE h.detail_id = :id ORDER BY h.time DESC", nativeQuery = true)
	List<History> findAllHistoriesByUser(@Param("id") int idDetail);
	
	@Query(value = "SELECT * FROM history h WHERE DATE(h.time) >= :startDate AND DATE(h.time) <= :endDate ORDER BY h.time DESC", nativeQuery = true)
	List<History> findAllHistoriesByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(value = "SELECT * FROM history h WHERE h.detail_id = :id AND DATE(h.time) >= :startDate AND DATE(h.time) <= :endDate ORDER BY h.time DESC", nativeQuery = true)
	List<History> findAllHistoriesByUserAndDate(@Param("id") int idDetail, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
}
