package com.example.demoS.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demoS.entity.UserAccount;

public interface UserRepository extends CrudRepository<UserAccount, Long>{
	@Query("SELECT u FROM UserAccount u WHERE u.email = :email")
    public UserAccount getUserByUsername(@Param("email") String username);
}
