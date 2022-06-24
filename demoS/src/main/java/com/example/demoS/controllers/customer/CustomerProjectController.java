package com.example.demoS.controllers.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoS.controllers.AbstractController;
import com.example.demoS.entity.CustomerAccount;
import com.example.demoS.entity.Field;
import com.example.demoS.entity.Project;
import com.example.demoS.entity.Tech;
import com.example.demoS.entity.UserAccount;
import com.example.demoS.service.CustomerAccountService;
import com.example.demoS.service.FieldService;
import com.example.demoS.service.ProjectService;
import com.example.demoS.service.TechService;
import com.example.demoS.service.UserAccountService;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.util.StringUtils;
@Controller
public class CustomerProjectController extends AbstractController {
	@Autowired
	ProjectService projectService;

	@Autowired
	FieldService fieldService;

	@Autowired
	TechService techService;

	@Autowired
	CustomerAccountService customerAccountService;

	@Autowired
	UserAccountService userAccountService;

	@ModelAttribute("scopes")
	public List<String> getScopes() {
		return Arrays.asList("Large", "Medium", "Small");
	}

	@ModelAttribute("durations")
	public List<String> getDurations() {
		return Arrays.asList("Less than 1 month", "1 to 3 months", "3 to 6 months", "More than 6 months");
	}

//	@RequestMapping("/customer/project/all")
//	public String allList(Model model) {
//		List<Project> projectList = projectService.findAll();
//		model.addAttribute("projectList", projectList);
//		return "customer/customerProjectList";
//
//	}
	@RequestMapping("/customer/project/all")
	public String allList(ModelMap model,
			@RequestParam(name ="titleSearch", required = false) String titleSearch,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id").descending());
		Page<Project> resultPage = null;

		if(StringUtils.hasText(titleSearch)) {
			resultPage = projectService.findAllProjectByTitleHiring(titleSearch, pageable);
			model.addAttribute("titleSearch", titleSearch);
		} else {
			resultPage = projectService.findAllProjectHiring(pageable);
		}

		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if(totalPages > 5) {
				if (end == totalPages) start = end - 5;
				else if (start == 1) end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());

			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("jobPage", resultPage);

		return "customer/project/index";
	}
	
	@RequestMapping("/customer/project")
	public String search(ModelMap model,
			@RequestParam(name ="titleSearch", required = false) String titleSearch,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		UserAccount currentAccount = super.getCurrentUser();
		CustomerAccount customerAccount = customerAccountService.getByUserAccount(currentAccount);
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id").descending());
		Page<Project> resultPage = null;

		if(StringUtils.hasText(titleSearch)) {
			resultPage = projectService.findAllProjectByTitleUserParam(customerAccount.getId(), titleSearch, pageable);
			model.addAttribute("titleSearch", titleSearch);
		} else {
			resultPage = projectService.findAllProjectUserParam(customerAccount.getId(), pageable);
		}

		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if(totalPages > 5) {
				if (end == totalPages) start = end - 5;
				else if (start == 1) end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());

			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("jobPage", resultPage);
		return "customer/project/index";
	}

//	@RequestMapping("/customer/project")
//	public String Customerlist(Model model) {
//		UserAccount currentAccount = super.getCurrentUser();
//		CustomerAccount customerAccount = customerAccountService.getByUserAccount(currentAccount);
//		List<Project> projectList = projectService.getByCustomerAccount(customerAccount);
//
//		model.addAttribute("projectList", projectList);
//
//		return "customer/customerProjectList";
//	}

	@RequestMapping("/customer/project/new")
	public String showNewProjectForm(Model model) {
		Project project = new Project();
		List<Tech> techList = techService.findAll();
		List<Field> fieldList = fieldService.findAll();

		model.addAttribute("project", project);
		model.addAttribute("techList", techList);
		model.addAttribute("fieldList", fieldList);
		return "customer/project/create";
	}

	@RequestMapping("/customer/project/detail/{id}")
	public ModelAndView projectDetail(ModelMap modelMap, @PathVariable("id") Long projectId) {
		UserAccount currentAccount = super.getCurrentUser();
		CustomerAccount customerAccount = customerAccountService.getByUserAccount(currentAccount);
		Optional<Project> opt = projectService.findById(projectId);

		if (opt.isPresent()) {
			Project entity = opt.get();
			if (entity.getCustomerAccount().getId() != customerAccount.getId()) {
				modelMap.addAttribute("message", "You can only edit your own job!");
				return new ModelAndView("forward:/customer/project", modelMap);
			}
			modelMap.addAttribute("techList", entity.getTech().toArray());
			modelMap.addAttribute("fieldList", entity.getField().getName());
			modelMap.addAttribute("project", entity);
			modelMap.addAttribute("customerAccount", customerAccount);
			return new ModelAndView("/customer/project/detail", modelMap);
		}
		modelMap.addAttribute("message", "Project is not exist");
		return new ModelAndView("forward:/customer/project", modelMap);
	}

	@RequestMapping("/customer/project/edit/{id}")
	public ModelAndView editProject(ModelMap modelMap, @PathVariable("id") Long projectId) {
		UserAccount currentAccount = super.getCurrentUser();
		CustomerAccount customerAccount = customerAccountService.getByUserAccount(currentAccount);
//		Project project = projectService.getById(projectId);


		Optional<Project> opt = projectService.findById(projectId);
		if (opt.isPresent()) {
			Project project = opt.get();
			if (project.getCustomerAccount().getId() != customerAccount.getId()) {
				modelMap.addAttribute("message", "You can only edit your own job!");
				return new ModelAndView("forward:/customer/project", modelMap);
				
			}
			List<String> projectTech = new ArrayList<String>();
			Set<Tech> techs = project.getTech();
			for (Tech tech : techs) {
				projectTech.add(tech.getName());
			}
			List<Tech> techList = techService.findAll();
			List<Field> fieldList = fieldService.findAll();
			modelMap.addAttribute("projectTech", projectTech);
			modelMap.addAttribute("techList", techList);
			modelMap.addAttribute("fieldList", fieldList);
			modelMap.addAttribute("project", project);
			return new ModelAndView("/customer/project/edit", modelMap);
		}
		modelMap.addAttribute("message", "Project is not exist !!");
		return new ModelAndView("forward:/customer/project", modelMap);
	}

	@RequestMapping("/customer/project/save")
	public String addNewProject(@ModelAttribute("project") Project project,
			@RequestParam(value = "fieldId", required = false) String fieldId,
			@RequestParam(value = "techChecked", required = false) String techs) {
		long fieldID = Long.parseLong(fieldId);
		Field fieldDTO = fieldService.getById(fieldID);
		List<String> myList = new ArrayList<String>(Arrays.asList(techs.split(",")));

		UserAccount currentAccount = super.getCurrentUser();
		CustomerAccount customerAccount = customerAccountService.getByUserAccount(currentAccount);

		project.setCustomerAccount(customerAccount);
		project.setField(fieldDTO);
		project.setStatus(1);
		for (String techName : myList) {
			project.getTech().add(techService.getByName(techName));
		}
		projectService.save(project);
		return "redirect:/customer/project";
	}
	
	@RequestMapping("/customer/project/delete/{id}")
	public String deleteProject(@PathVariable("id") long id) {
		Project project = projectService.getById(id);
		projectService.deActive(project);
		return "redirect:/customer/project";
	}

}
