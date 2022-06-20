package com.example.demoS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoS.entity.CustomerAccount;
import com.example.demoS.entity.UserAccount;


@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long>{

	CustomerAccount findByUserAccount(UserAccount userAccount);
}
