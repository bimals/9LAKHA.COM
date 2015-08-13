package blog.platform.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class UrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	protected final Log LOGGER = LogFactory.getLog(this.getClass());
	  
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		  String userName = request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
		  LOGGER.info(" Authentication Failed in GHixUrlAuthenticationFailure class");
		
		  // set userName into session 
		 
		  super.onAuthenticationFailure(request, response, exception);
	}
    
}