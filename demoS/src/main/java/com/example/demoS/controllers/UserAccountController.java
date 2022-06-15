package com.example.demoS.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoS.entity.UserAccount;
import com.example.demoS.model.UserAccountDTO;
import com.example.demoS.service.UserAccountService;




@Controller
public class UserAccountController {
	@Autowired
	private UserAccountService service;
	
	@RequestMapping("/user")
	public String list(Model model) {
		List<UserAccount> userAccountList = service.findAll();
		model.addAttribute("userAccountList",userAccountList);
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "create";
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST )
	public String saveUser(@ModelAttribute("userAccount") UserAccountDTO UserAccountDto) {
		UserAccount entity = new UserAccount();
		BeanUtils.copyProperties(UserAccountDto, entity);
		service.save(entity);
		return "redirect:/user";
	}
	
	
	@RequestMapping("/user/edit/{id}")
	public ModelAndView editUser(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("edit");
		UserAccount userAccount = service.getById(id);
		mav.addObject(userAccount);
		return mav;
	}
	
	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		service.deleteById(id);
		return "redirect:/user";
	}
	
}
