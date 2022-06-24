package com.example.demoS.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoS.entity.CustomerAccount;
import com.example.demoS.entity.Project;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	List<Project> findByCustomerAccount(CustomerAccount customerAccount);
	
	@Query(value = "SELECT * FROM Project j WHERE j.title LIKE %:title% AND j.status=1",
			countQuery = "SELECT count(*) FROM Project j WHERE j.title LIKE %:title% AND j.status=1",
			nativeQuery = true)
	Page<Project> findAllProjectByTitleHiring(@Param("title") String title, Pageable pageable);

	@Query(value = "SELECT * FROM Project j WHERE j.status=1",
			countQuery = "SELECT count(*) FROM Project j WHERE j.status = 1",
			nativeQuery = true)
	Page<Project> findAllProjectHiring(Pageable pageable);

	
	
	@Query(value = "SELECT * FROM Project j WHERE j.title LIKE %:title% AND j.status!=0 AND j.customerid = :customerid",
			countQuery = "SELECT count(*) FROM Project j WHERE j.title LIKE %:title% AND j.status!=0 AND j.customerid = :customerid",
			nativeQuery = true)
	Page<Project> findAllProjectByTitleUserParam(@Param("customerid") Long customerid, @Param("title") String title, Pageable pageable);

	@Query(value = "SELECT * FROM Project j WHERE j.customerid = :customerid AND j.status!=0",
			countQuery = "SELECT count(*) FROM Project j WHERE j.customerid = :customerid AND j.status!=0",
			nativeQuery = true)
	Page<Project> findAllProjectUserParam(@Param("customerid") Long customerid,Pageable pageable);
}
