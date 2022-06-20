package com.example.demoS.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.Company;
import com.example.demoS.repository.CompanyRepository;

import com.example.demoS.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public <S extends Company> S save(S entity) {
		return companyRepository.save(entity);
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Page<Company> findAll(Pageable pageable) {
		return companyRepository.findAll(pageable);
	}

	@Override
	public List<Company> findAll(Sort sort) {
		return companyRepository.findAll(sort);
	}

	@Override
	public List<Company> findAllById(Iterable<Long> ids) {
		return companyRepository.findAllById(ids);
	}

	@Override
	public Optional<Company> findById(Long id) {
		return companyRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return companyRepository.existsById(id);
	}

	@Override
	public <S extends Company> long count(Example<S> example) {
		return companyRepository.count(example);
	}

	@Override
	public long count() {
		return companyRepository.count();
	}

	@Override
	public <S extends Company> boolean exists(Example<S> example) {
		return companyRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		companyRepository.deleteById(id);
	}

	@Override
	public void delete(Company entity) {
		companyRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		companyRepository.deleteAll();
	}

	@Override
	public Company getById(Long id) {
		return companyRepository.getById(id);
	}
	
	
}
