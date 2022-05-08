package com.example.BalantaTaller1.auth;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
//import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UrlAuthenticationSuccessHandler implements /*AccessDeniedHandler*/ AuthenticationSuccessHandler {
	
	 protected Log logger = LogFactory.getLog(this.getClass());

	  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		  handle(request, response, authentication);
	      clearAuthenticationAttributes(request);
	}
	
	protected void handle(
	        HttpServletRequest request,
	        HttpServletResponse response, 
	        Authentication authentication
	) throws IOException {

	    String targetUrl = determineTargetUrl(authentication);

	    if (response.isCommitted()) {
	        logger.debug(
	                "Response has already been committed. Unable to redirect to "
	                        + targetUrl);
	        return;
	    }

	    redirectStrategy.sendRedirect(request, response, targetUrl);
	}
  
  	protected String determineTargetUrl(final Authentication authentication) {

	    Map<String, String> roleTargetUrlMap = new HashMap<>();
	    roleTargetUrlMap.put("ROLE_OPERATOR", "operator/operator.html");
	    roleTargetUrlMap.put("ROLE_ADMIN", "admin/admin.html");

	    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    for (final GrantedAuthority grantedAuthority : authorities) {
	        String authorityName = grantedAuthority.getAuthority();
	        if(roleTargetUrlMap.containsKey(authorityName)) {
	            return roleTargetUrlMap.get(authorityName);
	        }
	    }

	    throw new IllegalStateException();
	}
  	
  	protected void clearAuthenticationAttributes(HttpServletRequest request) {
  	    HttpSession session = request.getSession(false);
  	    if (session == null) {
  	        return;
  	    }
  	    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  	}
	
	/*
	 * protected Log logger = LogFactory.getLog(this.getClass());
	 * 
	 * private static Logger log =
	 * LoggerFactory.getLogger(UrlAuthenticationSuccessHandler.class);
	 * 
	 * @Override public void handle(HttpServletRequest request, HttpServletResponse
	 * response, AccessDeniedException ex) throws IOException, ServletException {
	 * 
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * 
	 * if (auth != null) { log.info(auth.getName() +
	 * " was trying to access protected resource: " + request.getRequestURI()); }
	 * 
	 * response.sendRedirect(request.getContextPath() + "/access-denied");
	 * 
	 * }
	 */

	
}
