package com.example.demoS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoS.entity.UserAccount;






@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{

}
