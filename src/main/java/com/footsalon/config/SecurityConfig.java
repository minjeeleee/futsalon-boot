package com.footsalon.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final DataSource dataSource;
	private final PasswordEncoder passwordEncoder;

	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
	
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring()
			.mvcMatchers("/static/**")
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.mvcMatchers(HttpMethod.GET,"/member/logout").authenticated()
			.mvcMatchers(HttpMethod.GET,"/team/create-form","/team/create","/team/join-team","/team/join").hasAuthority("normal")
			.mvcMatchers(HttpMethod.GET,"/team/member/**").hasAnyAuthority("member","leader")
			.mvcMatchers(HttpMethod.POST,"/match/apply-team-match","/match/team-match-form").hasAuthority("leader")
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginProcessingUrl("/member/login")
			.usernameParameter("userId")
			.loginPage("/member/login")
			.defaultSuccessUrl("/");
		
		http.csrf().ignoringAntMatchers("/mail");
		http.csrf().ignoringAntMatchers("/notice/**");
		http.csrf().ignoringAntMatchers("/member/**");
		http.csrf().ignoringAntMatchers("/location/**");
		http.csrf().ignoringAntMatchers("/match/**");
		http.csrf().ignoringAntMatchers("/team/**");
		
	}
	
}
