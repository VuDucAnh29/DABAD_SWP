package com.example.demoS.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demoS.entity.UserAccount;
import com.example.demoS.repository.RoleRepository;
import com.example.demoS.repository.UserAccountRepository;
import com.example.demoS.service.UserAccountService;



@Service
public class UserAccountServiceImpl implements UserAccountService{
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}
	
	@Override
	public <S extends UserAccount> S save(S entity) { 
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		entity.setUpdateDate(currentDate);
		entity.setPassword(encoder.encode(entity.getPassword()));
		return userAccountRepository.save(entity);
	}
	
	
	@Override
	public <S extends UserAccount> S save(S entity, String roleName) { 
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		entity.getRoles().add(roleRepository.getRoleByName(roleName));
		entity.setUpdateDate(currentDate);
		entity.setPassword(encoder.encode(entity.getPassword()));
		return userAccountRepository.save(entity);
	}
	
	@Override
	public <S extends UserAccount> S deActive(S entity) {
		entity.setStatus(0);
		return userAccountRepository.save(entity);
	}
	
	public UserAccount getByEmail(String email){
        return userAccountRepository.findByEmail(email);
    }

	@Override
	public List<UserAccount> findAll() {
		return userAccountRepository.findAll();
	}

	@Override
	public Page<UserAccount> findAll(Pageable pageable) {
		return userAccountRepository.findAll(pageable);
	}

	@Override
	public List<UserAccount> findAll(Sort sort) {
		return userAccountRepository.findAll(sort);
	}

	@Override
	public Optional<UserAccount> findById(Long id) {
		return userAccountRepository.findById(id);
	}

	@Override
	public <S extends UserAccount> List<S> saveAll(Iterable<S> entities) {
		return userAccountRepository.saveAll(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return userAccountRepository.existsById(id);
	}

	@Override
	public long count() {
		return userAccountRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		userAccountRepository.deleteById(id);
	}

	@Override
	public void delete(UserAccount entity) {
		userAccountRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		userAccountRepository.deleteAll();
	}

	@Override
	public UserAccount getById(Long id) {
		return userAccountRepository.getById(id);
	}



	
	
	
}
