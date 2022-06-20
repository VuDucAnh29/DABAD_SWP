package com.example.demoS.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDTO {
	private long id;
	private String email;
	private String password;
	private String fullName;
	private String avatar;
	private String createDate;
	private String updateDate;
	private double balance;
	private int status;
}




