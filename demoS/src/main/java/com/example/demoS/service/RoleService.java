package com.example.demoS.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demoS.entity.Role;

@Service
public interface RoleService {

	long count();

	Optional<Role> findById(Long id);

	<S extends Role> S save(S entity);

	Role getRoleById(Long id);

}
