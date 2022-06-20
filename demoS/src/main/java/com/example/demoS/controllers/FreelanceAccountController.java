package com.example.demoS.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demoS.entity.FreelancerAccount;
import com.example.demoS.entity.Tech;
import com.example.demoS.entity.UserAccount;
import com.example.demoS.service.FreelanceService;
import com.example.demoS.service.TechService;
import com.example.demoS.service.UserAccountService;

@Controller
public class FreelanceAccountController extends AbstractController{
	@Autowired
	TechService techService;
	
	@Autowired
	UserAccountService userAccountService;
	
	@Autowired
	FreelanceService freelanceService;
	
	@RequestMapping("/freelance")
	public String showCustomer(Model model) {
//		List<CustomerAccount> customerAccount = customerAccountservice.findAll();
		UserAccount currentAccount = super.getCurrentUser();

		FreelancerAccount freelancerAccount = freelanceService.getByUserAccount(currentAccount);
		Set<Tech> techs = freelancerAccount.getTech();
		List<String> techList = new ArrayList<String>();
		for (Tech tech : techs) {
			techList.add(tech.getName());
		}
		model.addAttribute("freelancerAccount", freelancerAccount);
		model.addAttribute("techList",techList);
		return "freelance/index";
	}
	
	@RequestMapping("/freelance/new")
	public String showFreelance(Model model) {
		UserAccount userAccount = new UserAccount();
		List<Tech> techList = techService.findAll();
		model.addAttribute("techList", techList);
		model.addAttribute("userAccount", userAccount);
		return "freelance/freelanceCreate";
	}
	
	@RequestMapping(value = "/freelance/save", method = RequestMethod.POST)
	public String saveFreelance(@ModelAttribute("userAccount") UserAccount userAccount,
								@RequestParam(value = "techChecked",required=false) String techs, 
								@RequestParam(value = "description" ,required = false) String overView,
								Model model) {
		FreelancerAccount freelanceAccount = new FreelancerAccount();
		List<String> myList = new ArrayList<String>(Arrays.asList(techs.split(",")));
//		List<Tech> techList = new ArrayList<Tech>();
//		for (String o : myList) {
//			techList.add(techService.getByName(o));
//		}
//		model.addAttribute("techList", techList);
//		String CustomerRole = "FREELANCE";
//		userAccountService.save(userAccount,CustomerRole);
//		
//		freelanceAccount.setUserAccount(userAccount);
//		for (String tech : myList) {
//			freelanceAccount.getTech().add(techService.getByName(tech));	
//		}
		String FreelancerRole = "FREELANCER";
		userAccountService.save(userAccount,FreelancerRole);
		for (String techName : myList) {
			freelanceAccount.getTech().add(techService.getByName(techName));
		}
		freelanceAccount.setOverview(overView);
		freelanceAccount.setUserAccount(userAccount);
		freelanceService.save(freelanceAccount);
		return "freelance/index";
	}
}
