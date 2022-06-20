package com.example.demoS.entity;
// Generated Jun 15, 2022, 11:29:00 AM by Hibernate Tools 3.6.2.Final



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tech")
public class Tech  {

	private long id;
	private String name;



	public Tech() {
	}

	public Tech(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Tech(int id, String name) {
		this.id = id;
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

	public void setName(String name) {
		this.name = name;
	}




}
