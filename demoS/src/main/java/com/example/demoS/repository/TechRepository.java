package com.example.demoS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoS.entity.Tech;
@Repository
public interface TechRepository extends JpaRepository<Tech, Long>{
	Tech findByName(String name);
}
