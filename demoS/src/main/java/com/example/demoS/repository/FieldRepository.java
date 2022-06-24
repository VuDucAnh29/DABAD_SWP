package com.example.demoS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demoS.entity.Field;


@Repository
public interface FieldRepository extends JpaRepository<Field, Long>{
	Field findByName(String name);
}
