package duclm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@Autowired
	HttpSession session;
	
	@RequestMapping("/")
	public String index() {
		if(session.getAttribute("email") != null && session.getAttribute("role").equals(2)) {
			return "customer/index";
		}
		if(session.getAttribute("email") != null && session.getAttribute("role").equals(3)) {
			return "freelancer/index";
		}
		
		return "site/index";
	}
}
