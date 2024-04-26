package com.example.ltn_admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ltn_admin.entity.Detail;


public interface DetailRepository extends JpaRepository<Detail, Integer> {
	@Query("SELECT d FROM Detail d WHERE d.licensePlate = :licensePlate")
	Optional<Detail> findByLicensePlate(@Param("licensePlate") String licensePlate);
}
