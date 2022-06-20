package com.example.demoS.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.Tech;
import com.example.demoS.repository.TechRepository;
import com.example.demoS.service.TechService;

@Service
public class TechServiceImpl implements TechService{
	@Autowired
	TechRepository techRepository;

	@Override
	public <S extends Tech> S save(S entity) {
		return techRepository.save(entity);
	}

	@Override
	public List<Tech> findAll() {
		return techRepository.findAll();
	}

	@Override
	public Page<Tech> findAll(Pageable pageable) {
		return techRepository.findAll(pageable);
	}

	@Override
	public List<Tech> findAll(Sort sort) {
		return techRepository.findAll(sort);
	}

	@Override
	public Optional<Tech> findById(Long id) {
		return techRepository.findById(id);
	}

	@Override
	public <S extends Tech> List<S> saveAll(Iterable<S> entities) {
		return techRepository.saveAll(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return techRepository.existsById(id);
	}

	@Override
	public long count() {
		return techRepository.count();
	}

	@Override
	public <S extends Tech> boolean exists(Example<S> example) {
		return techRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		techRepository.deleteById(id);
	}

	@Override
	public void delete(Tech entity) {
		techRepository.delete(entity);
	}

	@Override
	public Tech getById(Long id) {
		return techRepository.getById(id);
	}

	@Override
	public Tech getByName(String name) {
		
		return techRepository.findByName(name);
	}

	

	
	
}
