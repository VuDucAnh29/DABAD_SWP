package com.example.demoS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demoS.entity.UserAccount;
import com.example.demoS.service.impl.UserAccountServiceImpl;



public abstract class AbstractController {
	@Autowired
    UserAccountServiceImpl userService;
    /**
     * Get logged user
     *
     * @return net.vatri.freelanceplatform.models.User
     **/
    protected UserAccount getCurrentUser(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails == false) {
            return null;
        }
        String username = ((UserDetails) principal).getUsername();

        return  userService.getByEmail(username);
    }
}
