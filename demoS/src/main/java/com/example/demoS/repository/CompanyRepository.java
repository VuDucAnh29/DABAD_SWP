package com.example.demoS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoS.entity.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	
}
