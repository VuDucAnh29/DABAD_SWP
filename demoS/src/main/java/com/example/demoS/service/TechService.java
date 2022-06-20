package com.example.demoS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.Tech;

@Service
public interface TechService {

	Tech getById(Long id);

	void delete(Tech entity);

	void deleteById(Long id);

	<S extends Tech> boolean exists(Example<S> example);

	long count();

	boolean existsById(Long id);

	<S extends Tech> List<S> saveAll(Iterable<S> entities);

	Optional<Tech> findById(Long id);

	List<Tech> findAll(Sort sort);

	Page<Tech> findAll(Pageable pageable);

	List<Tech> findAll();

	<S extends Tech> S save(S entity);

	Tech getByName(String name);

}
