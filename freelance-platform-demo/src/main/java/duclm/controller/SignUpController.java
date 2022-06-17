package duclm.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import duclm.domain.User;
import duclm.model.UserDto;
import duclm.service.UserService;
import duclm.service.StorageService;

@Controller
public class SignUpController {
	
	@Autowired
	UserService userService;
	
	
	@Autowired
	StorageService storageService;
	
	@RequestMapping("signup")
	public String login(ModelMap model) {
		model.addAttribute("user", new UserDto());
		return "/site/signup";
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
			BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("site/signup");
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
		
		userService.save(entity);
		model.addAttribute("message","Registered an account successfully!");
		
		return new ModelAndView("forward:/signup",model);
	}
}
