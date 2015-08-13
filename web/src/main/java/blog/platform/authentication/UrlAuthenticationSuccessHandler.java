//package org.springframework.security.web.authentication.preauth;
package blog.platform.authentication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class UrlAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	  protected final Log logger = LogFactory.getLog(this.getClass());
	  protected String baseGhixAuthContext;
	  private RequestCache requestCache = new HttpSessionRequestCache();	

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.isNotBlank(request.getParameter(targetUrlParameter)))) {
            //requestCache.removeRequest(request, response); -> delegated the flow to SavedRequestAwareAuthenticationSuccessHandler.onAuthenticationSuccess
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }

        clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
        String targetUrl = savedRequest.getRedirectUrl();
        targetUrl = resetDefTargetUrl(request, targetUrl);
        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
    

	 protected String resetDefTargetUrl(HttpServletRequest request,String targetUrl) throws ServletException {
		String contextPath = request.getContextPath(); //  contextPath: /hix
		String contextPathUri=StringUtils.substringBefore(targetUrl, contextPath); // contextPathUri: http://localhost:8080
		String redirectUri=StringUtils.substringAfter(targetUrl, contextPath); // redirectUri: /account/user/addrole/csr?module=broker
		String ghixLoginSuccessRedirectUrl=this.getDefaultTargetUrl(); // ghixLoginSuccessRedirectUrl :/account/user/loginSuccess
		try{
			//if(contextPath.contains(baseGhixAuthContext) && !redirectUri.equals(loginSuccessUrl))
		    if( !targetUrl.equals(contextPathUri + ghixLoginSuccessRedirectUrl))
		 	{
		    	targetUrl=contextPathUri+contextPath+ghixLoginSuccessRedirectUrl;//+"?redirectUri="+redirectUri;
		 		logger.debug("User had authenticated, redirecting to ::"+targetUrl);
		 	}
		}catch ( Exception ex) {
			logger.error("resetDefTargetUrl:getRequestParameters:Exception", ex);
			throw new ServletException(ex);
		}
     	
       
		return targetUrl;
	}
	 
    
/*
	 protected String resetDefTargetUrl(HttpServletRequest request,String targetUrl) {
		String contextPath = request.getContextPath(); //  contextPath: /hix
		String contextPathUri=StringUtils.substringBefore(targetUrl, contextPath); // contextPathUri: http://localhost:8080
		String redirectUri=StringUtils.substringAfter(targetUrl, contextPath); // redirectUri: /account/user/addrole/csr?module=broker
		String ghixLoginSuccessRedirectUrl=this.getDefaultTargetUrl(); // ghixLoginSuccessRedirectUrl :/account/user/loginSuccess
		
      	//if(contextPath.contains(baseGhixAuthContext) && !redirectUri.equals(loginSuccessUrl))
	    if( !targetUrl.equals(contextPathUri + ghixLoginSuccessRedirectUrl))
	 	{
	    	redirectUri = StringUtils.replaceOnce(redirectUri, "?", "&");
	    	  targetUrl=contextPathUri+contextPath+ghixLoginSuccessRedirectUrl+"?redirectUri="+redirectUri;
	 		logger.debug("User had authenticated, redirecting to ::"+targetUrl);
	 	}
        
		return targetUrl;
	}*/

	
	public String getBaseGhixAuthContext() {
		return baseGhixAuthContext;
	}


	public void setBaseGhixAuthContext(String baseGhixAuthContext) {
		this.baseGhixAuthContext = baseGhixAuthContext;
	}


	/**
	 * @param request
	 * @return String in the form of encoded url with all the query string parameters
	 *
	 */
	private String migrateRequestParameters(HttpServletRequest request,String targetUrl) {
		
		String targetParamStr = StringUtils.substringAfter(targetUrl, "?");
		
		
		StringBuffer sb = new StringBuffer();
		int i=0;
		Enumeration e = request.getParameterNames();
		try {
			while( e.hasMoreElements() ){
				String key = (String) e.nextElement();
				String[] val = request.getParameterValues( key );
				/*if(i==0) {
					//sb.append(URLEncoder.encode("?","UTF-8"));
					sb.append("&");
					i++;
				 }
				 else {
					 //sb.append(URLEncoder.encode("&","UTF-8"));
					 sb.append("&");
				 }*/
				sb.append("&");// we just need ampersand for forming proper url
				sb.append(URLEncoder.encode(key,"UTF-8"));
				//sb.append(URLEncoder.encode("=","UTF-8"));
				sb.append("=");
				sb.append(URLEncoder.encode(val[0],"UTF-8"));
			}
		}
		catch ( UnsupportedEncodingException usee) {
			logger.error("CustomAbstractPreAuthenticatedProcessingFilter:getRequestParameters:UnsupportedEncodingException", usee);
		}
		catch ( Exception ex) {
			logger.error("CustomAbstractPreAuthenticatedProcessingFilter:getRequestParameters:Exception", ex);
		}

		return sb.toString();

	}
}