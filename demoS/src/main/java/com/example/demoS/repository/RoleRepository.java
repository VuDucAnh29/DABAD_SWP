package com.example.demoS.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoS.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	@Query("SELECT u FROM Role u WHERE u.id = :id")
    public Role getRoleById(@Param("id") Long id);
	
	@Query("SELECT u FROM Role u WHERE u.name = :name")
    public Role getRoleByName(@Param("name") String name);
}
