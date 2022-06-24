package com.example.demoS.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.Field;
import com.example.demoS.repository.FieldRepository;
import com.example.demoS.service.FieldService;

@Service
public class FieldServiceImpl implements FieldService{
	@Autowired
	FieldRepository fieldRepository;

	@Override
	public Field findByName(String name) {
		return fieldRepository.findByName(name);
	}

	@Override
	public <S extends Field> S save(S entity) {
		return fieldRepository.save(entity);
	}

	@Override
	public List<Field> findAll() {
		return fieldRepository.findAll();
	}

	@Override
	public Page<Field> findAll(Pageable pageable) {
		return fieldRepository.findAll(pageable);
	}

	@Override
	public List<Field> findAll(Sort sort) {
		return fieldRepository.findAll(sort);
	}

	@Override
	public Optional<Field> findById(Long id) {
		return fieldRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return fieldRepository.existsById(id);
	}

	@Override
	public <S extends Field> long count(Example<S> example) {
		return fieldRepository.count(example);
	}

	@Override
	public long count() {
		return fieldRepository.count();
	}

	@Override
	public <S extends Field> boolean exists(Example<S> example) {
		return fieldRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		fieldRepository.deleteById(id);
	}

	@Override
	public Field getById(Long id) {
		return fieldRepository.getById(id);
	}
	
	@Override
	public Field getByName(String name) {
		return fieldRepository.findByName(name);
	}
	
	
}
