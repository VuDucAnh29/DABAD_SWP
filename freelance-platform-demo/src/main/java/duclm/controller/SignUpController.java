package duclm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import duclm.domain.CustomerProfile;
import duclm.domain.FreelancerProfile;
import duclm.domain.User;
import duclm.model.TechDto;
import duclm.model.UserDto;
import duclm.service.UserService;
import duclm.service.CustomerProfileService;
import duclm.service.FreelancerProfileService;
import duclm.service.StorageService;
import duclm.service.TechService;

@Controller
public class SignUpController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	CustomerProfileService customerProfileService;
	
	@Autowired
	FreelancerProfileService freelancerProfileService;
	
	@Autowired
	TechService techService;
	
	@ModelAttribute("techs")
	public List<TechDto> getTechs() {
		return techService.findAll().stream().map(item->{
			TechDto dto = new TechDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@ModelAttribute("companies")
	public List<String> getScopes(){
		return Arrays.asList("FSoft","CMC Telecom","Viettel Telecom","VNPT","VNG","BKAV",
				"Intel","VCCorp","CyberSoft","CocCoc","Sailun","KMS Technology",
				"Bosch","Samsung","LG","Misa","Netlink");
	}
	
	@RequestMapping("signup")
	public String signup(ModelMap model) {
		return "/site/signup";
	}
	
	@RequestMapping("signup/customer")
	public String signupAsCustomer(ModelMap model) {
		model.addAttribute("user", new UserDto());
		return "/site/signupAsCustomer";
	}
	
	@RequestMapping("signup/freelancer")
	public String signupAsFreelancer(ModelMap model) {
		model.addAttribute("user", new UserDto());
		return "/site/signupAsFreelancer";
	}
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		
		Resource file = storageService.loadAsResource(filename);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@PostMapping("signup/saveAsCustomer")
	public ModelAndView saveAsCustomer(ModelMap model,
			@Valid @ModelAttribute("user") UserDto dto,
			@RequestParam("company") String company,
			BindingResult result) {
		
		if(userService.existsByEmail(dto.getEmail())) {
			model.addAttribute("message","This email has been used!");
			
			return new ModelAndView("forward:/signup/customer",model);
		}
		
		if(result.hasErrors()) {
			return new ModelAndView("site/signupAsCustomer");
		}
		
		dto.setRole(2);
		dto.setStatus(1);
		User entity = new User();
		BeanUtils.copyProperties(dto, entity);

		if(!dto.getAvatarFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			
			entity.setAvatar(storageService.getStoredFileName(dto.getAvatarFile(), uuString));
			storageService.store(dto.getAvatarFile(), entity.getAvatar());
		}
		
		CustomerProfile profile = new CustomerProfile();
		profile.setUser(entity);
		profile.setCompany(company);
		
		userService.save(entity);
		customerProfileService.save(profile);
		model.addAttribute("message","Registered an account successfully!");
		
		return new ModelAndView("forward:/signup/customer",model);
	}
	
	@PostMapping("signup/saveAsFreelancer")
	public ModelAndView saveAsFreelancer(ModelMap model,
			@Valid @ModelAttribute("user") UserDto dto,
			@RequestParam("techChecked") String techChecked,
			BindingResult result) {
		
		if(userService.existsByEmail(dto.getEmail())) {
			model.addAttribute("message","This email has been used!");
			
			return new ModelAndView("forward:/signup/freelancer",model);
		}
		
		if(result.hasErrors()) {
			return new ModelAndView("site/signupAsFreelancer");
		}
		
		dto.setRole(3);
		dto.setStatus(1);
		User entity = new User();
		BeanUtils.copyProperties(dto, entity);

		if(!dto.getAvatarFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			
			entity.setAvatar(storageService.getStoredFileName(dto.getAvatarFile(), uuString));
			storageService.store(dto.getAvatarFile(), entity.getAvatar());
		}
		
		String[] stringArray = techChecked.split(",");
		List<String> techCheckedList = Arrays.asList(stringArray);

		FreelancerProfile profile = new FreelancerProfile();
		profile.setUser(entity);
		
		for (String tech : techCheckedList) {		
			profile.getTechs().add(techService.findByName(tech));
		}
		
		userService.save(entity);
		freelancerProfileService.save(profile);
		model.addAttribute("message","Registered an account successfully!");
		
		return new ModelAndView("forward:/signup/freelancer",model);
	}
}
