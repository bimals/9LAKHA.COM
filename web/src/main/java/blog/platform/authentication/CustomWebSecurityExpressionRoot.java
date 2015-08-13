package blog.platform.authentication;

//import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

import blog.model.AccountUser;



public class CustomWebSecurityExpressionRoot extends WebSecurityExpressionRoot {
	
	//private static final Logger LOGGER = Logger.getLogger(CustomWebSecurityExpressionRoot.class);
	
    public CustomWebSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
        super(a, fi);
    }

    public boolean hasPermission( Object permission) {
		boolean result = false;
		try {
			//LOGGER.debug("JSP security tag: authorize:- Check the user :  "+authentication.getName() +" , hasPermission Permission : "+permission);
			if(!(authentication.getPrincipal() instanceof String)){
				AccountUser user = (AccountUser)authentication.getPrincipal();
				//result= user.getUserPermissions().contains((String)permission);
			}
			
		} catch (Exception e) {
			//LOGGER.error(" has Permission :; "+ e.getMessage());
		}
		//LOGGER.debug("JSP security tag: authorize:- for user "+ authentication.getName() +" result:-"+result);
		return result;
	}
}