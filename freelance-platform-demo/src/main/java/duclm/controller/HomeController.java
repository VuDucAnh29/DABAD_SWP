package duclm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import duclm.service.JobService;
import duclm.service.ProposalService;

@Controller
public class HomeController {
	@Autowired
	HttpSession session;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	ProposalService proposalService;
	
	@RequestMapping("/")
	public String index(ModelMap model) {
		if(session.getAttribute("email") != null && session.getAttribute("role").equals(2)) {
			Long currentUserId = Long.valueOf(session.getAttribute("userid").toString());
			int postedJobsCount = jobService.findCountAllJobByUserAvailable(currentUserId);
			int hiringJobsCount = jobService.findCountAllJobByUserStatus(currentUserId, 1);
			int ongoingJobsCount = jobService.findCountAllJobByUserStatus(currentUserId, 2);
			int completedJobsCount = jobService.findCountAllJobByUserStatus(currentUserId, 3);
			model.addAttribute("postedJobsCount",postedJobsCount);
			model.addAttribute("hiringJobsCount",hiringJobsCount);
			model.addAttribute("ongoingJobsCount",ongoingJobsCount);
			model.addAttribute("completedJobsCount",completedJobsCount);
			
			int proposalCounts = proposalService.findCountAllProposalsByJobUser(currentUserId);
			int rejectedProposalCounts = proposalService.findCountAllProposalsByJobUserStatus(currentUserId, 0);
			int processingProposalCounts = proposalService.findCountAllProposalsByJobUserStatus(currentUserId, 1);
			int approvedProposalCounts = proposalService.findCountAllProposalsByJobUserStatus(currentUserId, 2);
			model.addAttribute("proposalCounts",proposalCounts);
			model.addAttribute("rejectedProposalCounts",rejectedProposalCounts);
			model.addAttribute("processingProposalCounts",processingProposalCounts);
			model.addAttribute("approvedProposalCounts",approvedProposalCounts);
			
			return "customer/index";
		}
		if(session.getAttribute("email") != null && session.getAttribute("role").equals(3)) {
			Long currentUserId = Long.valueOf(session.getAttribute("userid").toString());
			int ongoingJobsCount = jobService.findCountAllJobsByFreelancerAndStatus(currentUserId, 2);
			int completedJobsCount = jobService.findCountAllJobsByFreelancerAndStatus(currentUserId, 3);
			model.addAttribute("ongoingJobsCount",ongoingJobsCount);
			model.addAttribute("completedJobsCount",completedJobsCount);
			
			int proposalCounts = proposalService.findCountAllProposalsByFreelancer(currentUserId);
			int rejectedProposalCounts = proposalService.findCountAllProposalsByFreelancer(currentUserId, 0);
			int processingProposalCounts = proposalService.findCountAllProposalsByFreelancer(currentUserId, 1);
			int approvedProposalCounts = proposalService.findCountAllProposalsByFreelancer(currentUserId, 2);
			model.addAttribute("proposalCounts",proposalCounts);
			model.addAttribute("rejectedProposalCounts",rejectedProposalCounts);
			model.addAttribute("processingProposalCounts",processingProposalCounts);
			model.addAttribute("approvedProposalCounts",approvedProposalCounts);
			return "freelancer/index";
		}
		
		return "site/index";
	}
}
