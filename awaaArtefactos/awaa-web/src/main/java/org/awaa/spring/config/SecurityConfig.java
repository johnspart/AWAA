/**
 * 
 */
package org.awaa.spring.config;

import java.util.Arrays;

import org.awaa.controller.security.CorsFilter;
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
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

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
		http.httpBasic();
		http.authorizeRequests().antMatchers("/login", "/forgot").permitAll();// .authenticated();
		http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
		http.csrf().csrfTokenRepository(csrfTokenRepository());
		http.formLogin().loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
				.successHandler(this.authenticationSuccessHandler());
		http.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
		http.logout().invalidateHttpSession(true).logoutUrl("/logout");
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
		// System.out.println("--Pass--"+bCryptPasswordEncoder.encode("123456"));
		return bCryptPasswordEncoder;
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new UrlAuthenticationSuccessHandler();
	}

}
