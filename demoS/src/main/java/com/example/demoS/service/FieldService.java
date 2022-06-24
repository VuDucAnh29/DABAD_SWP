package com.example.demoS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.Field;

@Service
public interface FieldService {


	Field getById(Long id);

	void deleteById(Long id);

	<S extends Field> boolean exists(Example<S> example);

	long count();

	<S extends Field> long count(Example<S> example);

	boolean existsById(Long id);

	Optional<Field> findById(Long id);

	List<Field> findAll(Sort sort);

	Page<Field> findAll(Pageable pageable);

	List<Field> findAll();

	<S extends Field> S save(S entity);

	Field findByName(String name);
	
	Field getByName(String name);

}
