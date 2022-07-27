package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

//@Configuration
//@EnableWebSecurity
public class NewSecurityConfig {
/*
	@Autowired
	private DataSource dataSource;
	
	// UserDetailService implementation class Initialize here
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }
	// AuthenticationManagerBuilder => chain-> UserDetails + password
	 
    
	// Filter Chain Security => HttpSecurity class chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      
        return http.build();
        
        
    }
	// Password Encryption
	
	// 
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }
	
	// Remember user to DB
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		// JdbcTokenRepositoryImpl -> auto insert username, series, token, last_used (date)
		db.setDataSource(dataSource);
		return db;
	}
	
	*/
}
