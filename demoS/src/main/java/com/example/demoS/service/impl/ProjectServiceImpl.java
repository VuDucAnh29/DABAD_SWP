package com.example.demoS.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.CustomerAccount;
import com.example.demoS.entity.Project;
import com.example.demoS.entity.UserAccount;
import com.example.demoS.repository.ProjectRepository;
import com.example.demoS.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public <S extends Project> S save(S entity) {
		String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		entity.setUpdateDate(currentDate);
		return projectRepository.save(entity);
	}
	
	@Override
	public <S extends Project> S deActive(S entity) {
		entity.setStatus(0);
		return projectRepository.save(entity);
	}

	@Override
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public Page<Project> findAll(Pageable pageable) {
		return projectRepository.findAll(pageable);
	}

	@Override
	public List<Project> findAll(Sort sort) {
		return projectRepository.findAll(sort);
	}

	@Override
	public Optional<Project> findById(Long id) {
		return projectRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return projectRepository.existsById(id);
	}

	@Override
	public <S extends Project> Page<S> findAll(Example<S> example, Pageable pageable) {
		return projectRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Project> long count(Example<S> example) {
		return projectRepository.count(example);
	}

	@Override
	public long count() {
		return projectRepository.count();
	}

	@Override
	public <S extends Project> boolean exists(Example<S> example) {
		return projectRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		projectRepository.deleteById(id);
	}

	@Override
	public Project getById(Long id) {
		return projectRepository.getById(id);
	}

	@Override
	public List<Project> getByCustomerAccount(CustomerAccount customerAccount) {
		
		return projectRepository.findByCustomerAccount(customerAccount);
	}

	@Override
	public Page<Project> findAllProjectHiring(Pageable pageable) {
		return projectRepository.findAllProjectHiring(pageable);
	}


	@Override
	public Page<Project> findAllProjectByTitleUserParam(Long customerid, String title, Pageable pageable) {
		return projectRepository.findAllProjectByTitleUserParam(customerid, title, pageable);
	}

	@Override
	public Page<Project> findAllProjectUserParam(Long customerid, Pageable pageable) {
		return projectRepository.findAllProjectUserParam(customerid, pageable);
	}

	@Override
	public Page<Project> findAllProjectByTitleHiring(String title, Pageable pageable) {
		return projectRepository.findAllProjectByTitleHiring(title, pageable);
	}
	
	
}
