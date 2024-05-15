package com.example.ltn_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ltn_admin.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	@Query(value = "SELECT * FROM account a WHERE a.user_name = :username AND a.password = :password", nativeQuery = true)
	Account findAccountByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
