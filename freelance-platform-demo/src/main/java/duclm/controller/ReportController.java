package duclm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
import duclm.domain.Job;
import duclm.domain.Proposal;
import duclm.domain.Report;
import duclm.domain.User;
import duclm.model.JobDto;
import duclm.model.ProposalDto;
import duclm.model.ReportDto;
import duclm.model.UserDto;
import duclm.service.UserService;
import duclm.service.JobService;
import duclm.service.ProposalService;
import duclm.service.ReportService;

@Controller
@RequestMapping("reports")
public class ReportController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	ReportService reportService;
	
	@PostMapping("save")
	public ModelAndView saveReport(ModelMap model,
			@ModelAttribute("report") ReportDto dto,
			@RequestParam(name ="jobId", required = true) Long jobId,
			@RequestParam(name ="freelancerId", required = true) Long freelancerId) {
		Report entity = new Report();		
		BeanUtils.copyProperties(dto, entity);
		entity.setJob(jobService.getById(jobId));
		entity.setFreelancer(userService.getById(freelancerId));
		entity.setStatus(1);
		
		if(reportService.findByJobId(jobId)!=null) {
			entity.setReportId(reportService.findByJobId(jobId).getReportId());
		}
		
		reportService.save(entity);

		model.addAttribute("message", "Report is saved!");
		
		return new ModelAndView("forward:/jobs/detail/" + dto.getJobId(),model);
	}
	
	@RequestMapping("reject/{id}")
	public ModelAndView reject(ModelMap model, @PathVariable("id") Long reportId) {
		Optional<Report> opt = reportService.findById(reportId);

		if(opt.isPresent()) {
			Report entity = opt.get();
			
			entity.setStatus(0);
			reportService.save(entity);
			
			return new ModelAndView("forward:/jobs/detail/" + entity.getJob().getJobId(),model);
		}
		
		model.addAttribute("message", "This report is not exist");
		return new ModelAndView("forward:/jobs", model);
	}
	
	@RequestMapping("approve/{id}")
	public ModelAndView approve(ModelMap model, @PathVariable("id") Long reportId) {
		Optional<Report> opt = reportService.findById(reportId);

		if(opt.isPresent()) {
			Report entity = opt.get();
			
			entity.setStatus(2);
			reportService.save(entity);
			
			return new ModelAndView("forward:/jobs/detail/" + entity.getJob().getJobId(),model);
		}
		
		model.addAttribute("message", "This report is not exist");
		return new ModelAndView("forward:/jobs", model);
	}
}
