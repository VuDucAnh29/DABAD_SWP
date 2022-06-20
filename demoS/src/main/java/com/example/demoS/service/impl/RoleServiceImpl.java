package com.example.demoS.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.Role;
import com.example.demoS.repository.RoleRepository;
import com.example.demoS.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role getRoleById(Long id) {
		return roleRepository.getRoleById(id);
	}

	@Override
	public <S extends Role> S save(S entity) {
		return roleRepository.save(entity);
	}

	@Override
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public long count() {
		return roleRepository.count();
	}
	
	
}
