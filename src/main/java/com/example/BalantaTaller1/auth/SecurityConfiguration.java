package com.example.BalantaTaller1.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UrlAuthenticationSuccessHandler accessDeniedHandler;

	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		
		/*http.formLogin().loginPage("/login").permitAll().and()
		.authorizeRequests()
		.antMatchers("/").authenticated()
		.antMatchers("/secure/**").authenticated()
		.antMatchers("/users/**").permitAll()
		.antMatchers("/admin/**").hasRole(UserType.administrator.toString())
		.antMatchers("/operator/**").hasRole(UserType.operator.toString())
		.anyRequest().authenticated().and()
		.httpBasic().and().logout().invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout").permitAll().and().exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);
		*/
		http.authorizeRequests().antMatchers("/secure/**").authenticated().anyRequest().permitAll().and().httpBasic().and().logout()
		.invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
		.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

	}

}
