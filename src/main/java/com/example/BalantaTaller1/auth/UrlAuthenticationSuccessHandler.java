package com.example.BalantaTaller1.auth;

import java.io.IOException;
/*import java.util.Collection;
import java.util.HashMap;
import java.util.Map;*/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextHolder;
/*import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;*/
import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UrlAuthenticationSuccessHandler implements AccessDeniedHandler {
	
	 private static Logger log = LoggerFactory.getLogger(UrlAuthenticationSuccessHandler.class);


	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	        if (auth != null) {
	            log.info(auth.getName()
	                    + " was trying to access protected resource: "
	                    + request.getRequestURI());
	        }

	        response.sendRedirect(request.getContextPath() + "/access-denied");

		
	}

	

}
