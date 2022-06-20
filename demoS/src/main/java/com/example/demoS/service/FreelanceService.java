package com.example.demoS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.FreelancerAccount;
import com.example.demoS.entity.UserAccount;

@Service
public interface FreelanceService {

	FreelancerAccount getById(Long id);

	void deleteById(Long id);

	<S extends FreelancerAccount> boolean exists(Example<S> example);

	long count();

	<S extends FreelancerAccount> long count(Example<S> example);

	boolean existsById(Long id);

	<S extends FreelancerAccount> List<S> saveAll(Iterable<S> entities);

	Optional<FreelancerAccount> findById(Long id);

	List<FreelancerAccount> findAll(Sort sort);

	Page<FreelancerAccount> findAll(Pageable pageable);

	List<FreelancerAccount> findAll();

	<S extends FreelancerAccount> S save(S entity);

	FreelancerAccount getByUserAccount(UserAccount userAccount);

}
