package blog.platform.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import blog.db.repository.IUserRepository;
import blog.model.AccountUser;


@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService,AuthenticationUserDetailsService {
	
	@Autowired IUserRepository userRepository;
	
		
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountUser domainUser = userRepository.findByUserName(username);
		return domainUser;
	}

	//@Override
	public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
		//LOGGER.info("Getting user details from loadUserDetails :: " );
		
		String username = token.getName();
		UserDetails domainUser = null;
		domainUser= loadUserByUsername(username);
		
		//LOGGER.info("Loaded user details object for ("+username+") ::");
		return domainUser;
	}
	
	private boolean isPrivilegedUser(AccountUser user){
		//Set<UserRole> userRoles = user.getUserRole();
		boolean isPrivilegedRole = true;
/*		UserRole tmp = null;
		Iterator<UserRole> cursor = userRoles.iterator();
		while(cursor.hasNext()){
			tmp = cursor.next();
			if(tmp.isDefaultRole()){
				if(tmp.getRole().getPrivileged() == 1){
					isPrivilegedRole = true;
					break;
				}
			}
		}*/
		return isPrivilegedRole;
	}
	
	
}
