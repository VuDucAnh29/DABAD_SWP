package com.example.demoS.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoS.entity.Role;
import com.example.demoS.entity.UserAccount;

import com.example.demoS.model.UserAccountDTO;
import com.example.demoS.service.RoleService;
import com.example.demoS.service.UserAccountService;






@Controller
public class UserAccountController extends AbstractController{
	@Autowired
	private UserAccountService userService;
	
	@Autowired
	private RoleService roleService;
	
//	@Autowired
//	private CustomerAccountService customerService;
	
	
	
	@RequestMapping("/user")
	public String list(Model model) {
		List<UserAccount> userAccountList = userService.findAll();
		model.addAttribute("userAccountList",userAccountList);
		return "admin/index";
	}
	
	@RequestMapping("/user/new")
	public String showNewUserForm(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "admin/create";
	}
	
	@RequestMapping(value = "/user/save",method = RequestMethod.POST )
	public String saveUser(@ModelAttribute("userAccount") UserAccountDTO UserAccountDto,
							@RequestParam(value = "roleId") String roleId) {
		UserAccount entity = new UserAccount();
		BeanUtils.copyProperties(UserAccountDto, entity);
		long roleID = Long.parseLong(roleId);
		Role role = roleService.getRoleById(roleID);
		Set<Role> roles = new HashSet<>(0);
		roles.add(role);
		
		entity.setRoles(roles);
		
		
		userService.save(entity);
		return "redirect:/user";
	}
	
	
	@RequestMapping("/user/edit/{id}")
	public ModelAndView editUser(@PathVariable("id") long id,Model model) {
		ModelAndView mav = new ModelAndView("admin/edit");
		UserAccount userAccount = userService.getById(id);
		List<String> roles = new ArrayList<String>();
		Set<Role> roleSet = userAccount.getRoles();
		for (Role role : roleSet) {
			roles.add(Long.toString(role.getId()));
		}
		model.addAttribute("roles",roles);
		mav.addObject(userAccount);
		return mav;
	}
	
	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		UserAccount userAccount = userService.getById(id);
		userService.deActive(userAccount);
		return "redirect:/user";
	}
	
}
