package com.example.BalantaTaller1.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){

		return new UrlAuthenticationSuccessHandler(); 
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin").password("{noop}admin").roles("ADMIN")
		.and()
		.withUser("op").password("{noop}op").roles("OPERATOR");
		
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		/*
		 * AccessDeniedHandler accessDeniedHandler = null; http.authorizeRequests()
		 * .antMatchers("/").authenticated() .antMatchers("/secure/**").authenticated()
		 * .antMatchers("/users/**").permitAll()
		 * .antMatchers("/admin/**").hasRole("ADMIN")
		 * .antMatchers("/operator/**").hasRole("OPERATOR")
		 * .anyRequest().authenticated() .and().httpBasic() .and().formLogin()
		 * .loginProcessingUrl("/login")
		 * .permitAll().and().exceptionHandling().accessDeniedHandler(
		 * accessDeniedHandler);
		 */

		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/operator/**").hasRole("OPERATOR").antMatchers("/h2-console/**").permitAll().and()
				.formLogin().loginProcessingUrl("/login").successHandler(myAuthenticationSuccessHandler());

		http.csrf().disable();

		http.headers().frameOptions().disable();

	}

}
