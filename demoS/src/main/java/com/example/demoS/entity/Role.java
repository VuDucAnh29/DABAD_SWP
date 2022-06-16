package com.example.demoS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	private long id;
	private String name;
	
	public Role() {
	}
	
	public Role(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Role(String name) {
		this.name = name;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "Name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String fullName) {
		this.name = fullName;
	}
}
