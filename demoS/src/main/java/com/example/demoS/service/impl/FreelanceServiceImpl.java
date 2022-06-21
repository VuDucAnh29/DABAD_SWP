package com.example.demoS.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.example.demoS.entity.FreelancerAccount;
import com.example.demoS.entity.UserAccount;
import com.example.demoS.repository.FreelanceAccountRepository;
import com.example.demoS.service.FreelanceService;

@Service
public class FreelanceServiceImpl implements FreelanceService{
	@Autowired
	FreelanceAccountRepository freelanceAccountRepository;

	@Override
	public <S extends FreelancerAccount> S save(S entity) {
		return freelanceAccountRepository.save(entity);
	}

	@Override
	public List<FreelancerAccount> findAll() {
		return freelanceAccountRepository.findAll();
	}

	@Override
	public Page<FreelancerAccount> findAll(Pageable pageable) {
		return freelanceAccountRepository.findAll(pageable);
	}

	@Override
	public List<FreelancerAccount> findAll(Sort sort) {
		return freelanceAccountRepository.findAll(sort);
	}

	@Override
	public Optional<FreelancerAccount> findById(Long id) {
		return freelanceAccountRepository.findById(id);
	}

	@Override
	public <S extends FreelancerAccount> List<S> saveAll(Iterable<S> entities) {
		return freelanceAccountRepository.saveAll(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return freelanceAccountRepository.existsById(id);
	}

	@Override
	public <S extends FreelancerAccount> long count(Example<S> example) {
		return freelanceAccountRepository.count(example);
	}

	@Override
	public long count() {
		return freelanceAccountRepository.count();
	}

	@Override
	public <S extends FreelancerAccount> boolean exists(Example<S> example) {
		return freelanceAccountRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		freelanceAccountRepository.deleteById(id);
	}

	@Override
	public FreelancerAccount getById(Long id) {
		return freelanceAccountRepository.getById(id);
	}
	
	@Override
	public FreelancerAccount getByUserAccount(UserAccount userAccount) {
		return freelanceAccountRepository.findByUserAccount(userAccount);
	}
	
	
}
