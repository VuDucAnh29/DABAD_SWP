package com.example.demoS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.Company;

@Service
public interface CompanyService {

	Company getById(Long id);

	void deleteAll();

	void delete(Company entity);

	void deleteById(Long id);

	<S extends Company> boolean exists(Example<S> example);

	long count();

	<S extends Company> long count(Example<S> example);

	boolean existsById(Long id);

	Optional<Company> findById(Long id);

	List<Company> findAllById(Iterable<Long> ids);

	List<Company> findAll(Sort sort);

	Page<Company> findAll(Pageable pageable);

	List<Company> findAll();

	<S extends Company> S save(S entity);

}
