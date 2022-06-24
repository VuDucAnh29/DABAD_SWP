package com.example.demoS.controllers.freelancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoS.controllers.AbstractController;
import com.example.demoS.entity.FreelancerAccount;
import com.example.demoS.entity.Tech;
import com.example.demoS.entity.UserAccount;
import com.example.demoS.service.FreelanceService;
import com.example.demoS.service.TechService;
import com.example.demoS.service.UserAccountService;

@Controller
public class FreelanceAccountController extends AbstractController {
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
		model.addAttribute("techList", techList);
		return "freelance/index";
	}

//	@RequestMapping("/signup/freelance")
//	public String showFreelance(Model model) {
//		UserAccount userAccount = new UserAccount();
//		List<Tech> techList = techService.findAll();
//		model.addAttribute("techList", techList);
//		model.addAttribute("userAccount", userAccount);
//		return "freelance/signup";
//	}

//	@RequestMapping(value = "/freelance/save", method = RequestMethod.POST)
//	public String saveFreelance(@ModelAttribute("userAccount") UserAccount userAccount,
//			@RequestParam(value = "techChecked", required = false) String techs,
//			@RequestParam(value = "description", required = false) String overView,
//			@RequestParam(value = "freelanceId", required = false) String freelanceId, Model model) {
//		FreelancerAccount freelanceAccount = new FreelancerAccount();
//		List<String> myList = new ArrayList<String>(Arrays.asList(techs.split(",")));
//		if (freelanceId != "" && freelanceId != null) {
//			long freelanceID = Long.parseLong(freelanceId);
//			freelanceAccount.setId(freelanceID);
//		}
//		String FreelancerRole = "FREELANCER";
//		userAccountService.save(userAccount, FreelancerRole);
//		for (String techName : myList) {
//			freelanceAccount.getTech().add(techService.getByName(techName));
//		}
//
//		freelanceAccount.setOverview(overView);
//		freelanceAccount.setUserAccount(userAccount);
//		freelanceService.save(freelanceAccount);
//		return "redirect:/";
//	}

	@RequestMapping("/freelance/edit/{id}")
	public ModelAndView editUser(@PathVariable("id") long id, Model model) {
		ModelAndView mav = new ModelAndView("freelance/edit");
//		FreelancerAccount freelanceAccount = freelanceService.getById(id);
		UserAccount currentAccount = super.getCurrentUser();
		FreelancerAccount freelanceAccount = freelanceService.getByUserAccount(currentAccount);
		if(freelanceAccount.getId() != id) {
			model.addAttribute("message", "You can only edit your own profile!");		
			return new ModelAndView("site/errorPage");
		}
		List<Tech> techList = techService.findAll();

		List<String> freelanceTech = new ArrayList<String>();
		Set<Tech> techs = freelanceAccount.getTech();
		for (Tech tech : techs) {
			freelanceTech.add(tech.getName());
		}

		UserAccount userAccount = freelanceAccount.getUserAccount();
		String freelanceOverview = freelanceAccount.getOverview();

		model.addAttribute("freelanceOverview", freelanceOverview);
		model.addAttribute("techList", techList);
		model.addAttribute("freelanceTech", freelanceTech);
		model.addAttribute("userAccount", userAccount);

		mav.addObject("freelanceAccount", freelanceAccount);
		return mav;
	}
}
