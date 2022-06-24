package com.example.demoS.controllers.customer;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoS.controllers.AbstractController;
import com.example.demoS.entity.Company;
import com.example.demoS.entity.CustomerAccount;

import com.example.demoS.entity.UserAccount;
import com.example.demoS.service.CompanyService;
import com.example.demoS.service.CustomerAccountService;
import com.example.demoS.service.UserAccountService;

@Controller
public class CustomerAccountController extends AbstractController{
	@Autowired
	CustomerAccountService customerAccountService;

	@Autowired
	UserAccountService userAccountService;
	
	@Autowired
	CompanyService companyService;

//	@RequestMapping("/signup/customer")
//	public String showNewCustomerForm(Model model) {
//		UserAccount userAccount = new UserAccount();
//		List<Company> companys = companyService.findAll();
//		model.addAttribute("companys",companys);
//		model.addAttribute("userAccount", userAccount);
//		
//		return "customer/signup";
//	}

	@RequestMapping("/customer")
	public String showCustomer(Model model) {
//		List<CustomerAccount> customerAccount = customerAccountservice.findAll();
		UserAccount currentAccount = super.getCurrentUser();
		CustomerAccount customerAccount = customerAccountService.getByUserAccount(currentAccount);
		model.addAttribute("customerAccount", customerAccount);
		return "customer/index";
	}

//	@RequestMapping(value = "/customer/save", method = RequestMethod.POST)
//	public String saveUser(@ModelAttribute("userAccount") UserAccount userAccount,
//							@RequestParam(value = "companySelect",required=false) String company,
//							@RequestParam(value = "customerId", required = false) String customerId) {
//		CustomerAccount customerAccount = new CustomerAccount();
//		if(customerId != "" && customerId != null) {
//			long customerID = Long.parseLong(customerId);
//			customerAccount.setId(customerID);
//		}
//		String CustomerRole = "CUSTOMER";
//		userAccountService.save(userAccount,CustomerRole);
//		long companyId = Long.parseLong(company);
//		Company companyDTO = companyService.getById(companyId);
//		customerAccount.setCompany(companyDTO);
//		customerAccount.setUserAccount(userAccount);
//	
//		customerAccountService.save(customerAccount);
//		
//		return "redirect:/customer";
//	}
	
	
	@RequestMapping("/customer/edit/{id}")
	public ModelAndView editUser(@PathVariable("id") long id,Model model) {
		ModelAndView mav = new ModelAndView("customer/edit");
//		CustomerAccount customerAccount = customerAccountService.getById(id);
		UserAccount currentAccount = super.getCurrentUser();
		CustomerAccount customerAccount = customerAccountService.getByUserAccount(currentAccount);
		if(customerAccount.getId() != id) {
			model.addAttribute("message", "You can only edit your own profile!");		
			return new ModelAndView("site/errorPage");
		}
		List<Company> companys = companyService.findAll();
		UserAccount userAccount = customerAccount.getUserAccount(); 
		
		model.addAttribute("companys",companys);
		model.addAttribute("userAccount", userAccount);

		mav.addObject(customerAccount);
		return mav;
	}

}
