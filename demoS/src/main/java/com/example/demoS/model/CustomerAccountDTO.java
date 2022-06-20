package com.example.demoS.model;



import com.example.demoS.entity.Company;

import com.example.demoS.entity.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccountDTO {
	private long id;
	private UserAccount userAccount;
	private Company company;

}
