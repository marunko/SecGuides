package com.example.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.services.UserDetailServiceImpl;


// Old version deprecated after 2.7.0 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter   {
	 
	@Autowired
	private DataSource dataSource;
	// UserDetailService implementation class Initialize here
	@Autowired 
	private UserDetailServiceImpl userDetailsService;
	
		// AuthenticationManagerBuilder => chain-> UserDetails + password
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
		// Filter Chain Security => HttpSecurity class chain
	@Override	
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		// The pages does not require login
		http.authorizeRequests().antMatchers("/", "/form", "/logout", "/register").permitAll();

		// /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
		// If no login, it will redirect to /login page.
	 http.authorizeRequests().antMatchers("/info").access("hasAuthority('user')");

		// For ADMIN only.
		http.authorizeRequests().antMatchers("/admin").access("hasAuthority('admin')");

		// When the user has logged in as XX.
		// But access a page that requires role YY,
		// AccessDeniedException will be thrown.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Config for Login Form
		http.authorizeRequests().and().formLogin()//
				// Submit URL of login page.
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/form")//
				.defaultSuccessUrl("/info")//
				.failureUrl("/form?error=true")//
				.usernameParameter("username")//
				.passwordParameter("password")
				// Config for Logout Page
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
	// Filter Before 
		  http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.NEVER));
		
		// Config Remember Me.
		http.authorizeRequests().and() //
				.rememberMe().tokenRepository(this.persistentTokenRepository()) //
				.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
		 
	}
	
		// Password Encryption
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		 
		return new BCryptPasswordEncoder();
	}
	
	// Persistent Token Repository
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
}
