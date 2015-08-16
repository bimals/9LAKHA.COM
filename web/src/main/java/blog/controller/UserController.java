package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.db.repository.IUserRepository;
import blog.model.AccountUser;

@Controller
public class UserController {
	
	@Autowired IUserRepository userRepository;
	
	@RequestMapping(value="/account/user/login", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request) {

		return "login";
	}
	
	@RequestMapping(value="/account/user/login", method = RequestMethod.POST)
	public String login(@RequestBody AccountUser user, HttpServletRequest request) {
		String userName = user.getUserName();
		AccountUser accountUser = userRepository.findByUserName(userName);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object loggedInUser = (auth != null) ? auth.getPrincipal() :  null;
		if(accountUser != null) {
			return "forward:/account/user/loginSuccess";
		}
		return "login";
	}
	
	@RequestMapping(value="/account/user/loginSuccess")
	public String loginSuccess() {
		
		return "welcome";
	}

	@RequestMapping(value="/account/user/checklogin", method = RequestMethod.GET, produces = {"text/plain", "application/*"})
	@ResponseBody
	public String checkLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object loggedInUser = (auth != null) ? auth.getPrincipal() :  null;
		
		if (loggedInUser instanceof AccountUser) {
			return "true";
		}
		else {
			return "false";
		}
		
	}
}
