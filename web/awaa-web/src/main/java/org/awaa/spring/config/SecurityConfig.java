/**
 * 
 */
package org.awaa.spring.config;

import java.util.Arrays;

import org.awaa.services.administracion.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author John
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.formLogin().loginPage("/login.html");
		 * http.formLogin().defaultSuccessUrl("/index.html");
		 * http.formLogin().failureUrl("/login.html?login_error=1");
		 * http.formLogin().loginProcessingUrl("/login");
		 * http.formLogin().usernameParameter("username");
		 * http.formLogin().passwordParameter("password");
		 * http.logout().logoutSuccessUrl("/login.html").invalidateHttpSession(
		 * true).logoutUrl("/logout");
		 * http.exceptionHandling().accessDeniedPage("/accesoDenegado.html");
		 * 
		 * 
		 */
		http.authorizeRequests().antMatchers("/index.html").permitAll().and().formLogin();
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.authenticationProvider(authenticationProvider()).userDetailsService(userDetailsService());
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Arrays.asList(authenticationProvider()));
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(getBCryptPasswordEncoder());
		return authenticationProvider;
	}

	@Bean(name = "BCryptPasswordEncoder")
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}
