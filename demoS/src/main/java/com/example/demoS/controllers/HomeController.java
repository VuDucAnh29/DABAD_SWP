package com.example.demoS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demoS.entity.UserAccount;

@Controller
public class HomeController extends AbstractController{
	@RequestMapping("/")
	public String home(Model model) {
		UserAccount currentAccount = super.getCurrentUser();
		model.addAttribute("currentUser", currentAccount);
		return "site/welcome";
	}
	
	@RequestMapping("/403")
	public String errorPage(Model model) {
		return "site/errorPage";
	}
	
//	@RequestMapping("/login")
//	public String loginPage(Model model) {
//		return "site/login";
//	}
}
