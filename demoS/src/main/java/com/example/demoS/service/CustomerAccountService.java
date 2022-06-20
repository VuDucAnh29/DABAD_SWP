package com.example.demoS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.CustomerAccount;
import com.example.demoS.entity.UserAccount;

@Service
public interface CustomerAccountService {

	<S extends CustomerAccount> List<S> findAll(Example<S> example, Sort sort);

	<S extends CustomerAccount> List<S> findAll(Example<S> example);

	CustomerAccount getById(Long id);

	void delete(CustomerAccount entity);

	void deleteById(Long id);

	long count();

	<S extends CustomerAccount> Page<S> findAll(Example<S> example, Pageable pageable);

	boolean existsById(Long id);

	<S extends CustomerAccount> List<S> saveAll(Iterable<S> entities);

	Optional<CustomerAccount> findById(Long id);

	List<CustomerAccount> findAll(Sort sort);

	Page<CustomerAccount> findAll(Pageable pageable);

	List<CustomerAccount> findAll();

	<S extends CustomerAccount> S save(S entity);
	
	CustomerAccount getByUserAccount(UserAccount userAccount);
	
}
