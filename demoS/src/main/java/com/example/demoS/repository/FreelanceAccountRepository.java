package com.example.demoS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demoS.entity.FreelancerAccount;
import com.example.demoS.entity.UserAccount;

@Repository
public interface FreelanceAccountRepository extends JpaRepository<FreelancerAccount, Long>{
	FreelancerAccount findByUserAccount(UserAccount userAccount);
}
