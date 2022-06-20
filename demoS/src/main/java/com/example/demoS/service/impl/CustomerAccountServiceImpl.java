package com.example.demoS.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.CustomerAccount;
import com.example.demoS.entity.UserAccount;
import com.example.demoS.repository.CustomerAccountRepository;
import com.example.demoS.service.CustomerAccountService;



@Service
public class CustomerAccountServiceImpl implements CustomerAccountService{
	@Autowired
	private CustomerAccountRepository customerAccountRepository;

	@Override
	public <S extends CustomerAccount> S save(S entity) {
		return customerAccountRepository.save(entity);
	}

	@Override
	public List<CustomerAccount> findAll() {
		return customerAccountRepository.findAll();
	}
	
	

	@Override
	public Page<CustomerAccount> findAll(Pageable pageable) {
		return customerAccountRepository.findAll(pageable);
	}

	@Override
	public List<CustomerAccount> findAll(Sort sort) {
		return customerAccountRepository.findAll(sort);
	}

	@Override
	public Optional<CustomerAccount> findById(Long id) {
		return customerAccountRepository.findById(id);
	}

	@Override
	public <S extends CustomerAccount> List<S> saveAll(Iterable<S> entities) {
		return customerAccountRepository.saveAll(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return customerAccountRepository.existsById(id);
	}

	@Override
	public <S extends CustomerAccount> Page<S> findAll(Example<S> example, Pageable pageable) {
		return customerAccountRepository.findAll(example, pageable);
	}

	@Override
	public long count() {
		return customerAccountRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		customerAccountRepository.deleteById(id);
	}

	@Override
	public void delete(CustomerAccount entity) {
		customerAccountRepository.delete(entity);
	}

	@Override
	public CustomerAccount getById(Long id) {
		return customerAccountRepository.getById(id);
	}

	@Override
	public <S extends CustomerAccount> List<S> findAll(Example<S> example) {
		return customerAccountRepository.findAll(example);
	}

	@Override
	public <S extends CustomerAccount> List<S> findAll(Example<S> example, Sort sort) {
		return customerAccountRepository.findAll(example, sort);
	}

	@Override
	public CustomerAccount getByUserAccount(UserAccount userAccount) {
		return customerAccountRepository.findByUserAccount(userAccount);
	}

	
	
}
