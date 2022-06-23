package duclm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import duclm.interceptor.CustomerAuthenticationInterceptor;
import duclm.interceptor.FreelancerAuthenticationInterceptor;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private CustomerAuthenticationInterceptor customerAuthenticationInterceptor;
	
	@Autowired
	private FreelancerAuthenticationInterceptor freelancerAuthenticationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(customerAuthenticationInterceptor)
		.addPathPatterns("/customer/**");
		
		registry.addInterceptor(freelancerAuthenticationInterceptor)
		.addPathPatterns("/freelancer/**");
	}
	
}
