package duclm.controller.customer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import duclm.domain.Job;
import duclm.domain.User;
import duclm.model.FieldDto;
import duclm.model.JobDto;
import duclm.model.TechDto;
import duclm.model.UserDto;
import duclm.service.UserService;
import duclm.service.FieldService;
import duclm.service.JobService;
import duclm.service.StorageService;
import duclm.service.TechService;

@Controller
@RequestMapping("customer/jobs")
public class JobController {
	@Autowired
	JobService jobService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FieldService fieldService;
	
	@Autowired
	TechService techService;
	
	@Autowired
	HttpSession session;
	
	@ModelAttribute("fields")
	public List<FieldDto> getFields(){
		return fieldService.findAll().stream().map(item->{
			FieldDto dto = new FieldDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@ModelAttribute("techs")
	public List<TechDto> getTechs() {
		return techService.findAll().stream().map(item->{
			TechDto dto = new TechDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@ModelAttribute("scopes")
	public List<String> getScopes(){
		return Arrays.asList("Large","Medium","Small");
	}
	
	@ModelAttribute("durations")
	public List<String> getDurations(){
		return Arrays.asList("Less than 1 month","1 to 3 months","3 to 6 months","More than 6 months");
	}
	
	@RequestMapping("")
	public String list(ModelMap model) {
		Long userId = Long.valueOf(session.getAttribute("userid").toString());
		List<Job> list = jobService.findAllByUserId(userId);
		
		model.addAttribute("myjobs", list);
		
		return "customer/jobs/mylist";
	}
	
	@GetMapping("add")
	public String add(Model model) {
		JobDto dto = new JobDto();
		model.addAttribute("job", dto);
		
		return "customer/jobs/addNew";
	}
}
