package blog.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import blog.db.repository.IUserRepository;
import blog.model.AccountUser;

@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService {

	private static final AccountUser AccountUser = null;
	@Autowired IUserRepository userRepository;

	public UserDetails findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	public AccountUser getLoggedInUser() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object loggedInUser = (auth != null) ? auth.getPrincipal() :  null;
		
		if (loggedInUser instanceof AccountUser) {
			return (AccountUser) loggedInUser;
		}
		else {
			throw new Exception("User Not Found");
		}
	}

	public void updateUser(blog.model.AccountUser user) {
		userRepository.update(user);
		
	}

}
