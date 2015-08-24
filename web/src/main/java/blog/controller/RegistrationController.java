package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bimalsahay.model.AccountUser;
import com.bimalsahay.repository.IUserRepository;


@Controller
public class RegistrationController {

	@Autowired private IUserRepository userRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/account/user/new", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request) {
		return "register";
	}

	@RequestMapping(value="/account/user/create", method = RequestMethod.POST)
	public String createUser(@RequestBody AccountUser user) throws Exception {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));		
		AccountUser u = userRepository.findByUserName(user.getUserName());
		if(u == null) {
			userRepository.createUser(user);
		}
		else {
			throw new Exception("User Name is not unique");
		}
		
		return "redirect:/account/user/loginSuccess";
	}
}
