package com.example.demoS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDTO {
	private long id;
    private String fullName;
    private int roleID;
    private String password;
    private String Email;
    private String Avatar;
    private String CreateDate;
    private String UpdateDate;
    private double Balance;
    private int status;
}




