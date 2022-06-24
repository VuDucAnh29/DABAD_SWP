package com.example.demoS.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demoS.entity.Company;
import com.example.demoS.entity.CustomerAccount;
import com.example.demoS.entity.FreelancerAccount;
import com.example.demoS.entity.Tech;
import com.example.demoS.entity.UserAccount;
import com.example.demoS.service.CompanyService;
import com.example.demoS.service.CustomerAccountService;
import com.example.demoS.service.FreelanceService;
import com.example.demoS.service.TechService;
import com.example.demoS.service.UserAccountService;

@Controller
public class SignUpController {
	@Autowired
	CompanyService companyService;
	
	@Autowired
	TechService techService;

	@Autowired
	UserAccountService userAccountService;

	@Autowired
	FreelanceService freelanceService;
	
	@Autowired
	CustomerAccountService customerAccountService;
	
	@RequestMapping("/signup/freelance")
	public String showFreelance(Model model) {
		UserAccount userAccount = new UserAccount();
		List<Tech> techList = techService.findAll();
		model.addAttribute("techList", techList);
		model.addAttribute("userAccount", userAccount);
		return "freelance/signup";
	}
	
	@RequestMapping("/signup/customer")
	public String showNewCustomerForm(Model model) {
		UserAccount userAccount = new UserAccount();
		List<Company> companys = companyService.findAll();
		model.addAttribute("companys",companys);
		model.addAttribute("userAccount", userAccount);
		
		return "customer/signup";
	}
	
	@RequestMapping(value = "/signup/freelance/save", method = RequestMethod.POST)
	public String saveFreelance(@ModelAttribute("userAccount") UserAccount userAccount,
			@RequestParam(value = "techChecked", required = false) String techs,
			@RequestParam(value = "description", required = false) String overView,
			@RequestParam(value = "freelanceId", required = false) String freelanceId, Model model) {
		FreelancerAccount freelanceAccount = new FreelancerAccount();
		List<String> myList = new ArrayList<String>(Arrays.asList(techs.split(",")));
		if (freelanceId != "" && freelanceId != null) {
			long freelanceID = Long.parseLong(freelanceId);
			freelanceAccount.setId(freelanceID);
		}
		String FreelancerRole = "FREELANCER";
		userAccountService.save(userAccount, FreelancerRole);
		for (String techName : myList) {
			freelanceAccount.getTech().add(techService.getByName(techName));
		}

		freelanceAccount.setOverview(overView);
		freelanceAccount.setUserAccount(userAccount);
		freelanceService.save(freelanceAccount);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/signup/customer/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("userAccount") UserAccount userAccount,
							@RequestParam(value = "companySelect",required=false) String company,
							@RequestParam(value = "customerId", required = false) String customerId) {
		CustomerAccount customerAccount = new CustomerAccount();
		if(customerId != "" && customerId != null) {
			long customerID = Long.parseLong(customerId);
			customerAccount.setId(customerID);
		}
		String CustomerRole = "CUSTOMER";
		userAccountService.save(userAccount,CustomerRole);
		long companyId = Long.parseLong(company);
		Company companyDTO = companyService.getById(companyId);
		customerAccount.setCompany(companyDTO);
		customerAccount.setUserAccount(userAccount);
	
		customerAccountService.save(customerAccount);
		
		return "redirect:/";
	}
}
