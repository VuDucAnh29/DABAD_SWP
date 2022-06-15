package com.example.demoS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.UserAccount;




@Service
public interface UserAccountService {

	UserAccount getById(Long id);

	void deleteAll();

	void delete(UserAccount entity);

	void deleteById(Long id);

	long count();

	boolean existsById(Long id);

	<S extends UserAccount> List<S> saveAll(Iterable<S> entities);

	Optional<UserAccount> findById(Long id);

	List<UserAccount> findAll(Sort sort);

	Page<UserAccount> findAll(Pageable pageable);

	List<UserAccount> findAll();

	<S extends UserAccount> S save(S entity);

}
