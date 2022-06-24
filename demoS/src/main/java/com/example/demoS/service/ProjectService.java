package com.example.demoS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.CustomerAccount;
import com.example.demoS.entity.Project;

@Service
public interface ProjectService {

	Project getById(Long id);

	void deleteById(Long id);

	<S extends Project> boolean exists(Example<S> example);

	long count();

	<S extends Project> long count(Example<S> example);

	<S extends Project> Page<S> findAll(Example<S> example, Pageable pageable);

	boolean existsById(Long id);

	Optional<Project> findById(Long id);

	List<Project> findAll(Sort sort);

	Page<Project> findAll(Pageable pageable);

	List<Project> findAll();

	<S extends Project> S save(S entity);
	
	List<Project> getByCustomerAccount(CustomerAccount customerAccount);
	
	Page<Project> findAllProjectByTitleHiring(String title, Pageable pageable);

	Page<Project> findAllProjectHiring(Pageable pageable);

	Page<Project> findAllProjectUserParam(Long customerid, Pageable pageable);

	Page<Project> findAllProjectByTitleUserParam(Long customerid, String title, Pageable pageable);

	<S extends Project> S deActive(S entity);

}
