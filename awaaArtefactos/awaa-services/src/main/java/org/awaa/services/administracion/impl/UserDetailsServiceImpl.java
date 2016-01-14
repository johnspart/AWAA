/**
 * 
 */
package org.awaa.services.administracion.impl;

import java.util.ArrayList;
import java.util.List;

import org.awaa.repository.administracion.UsuarioRepository;
import org.awwa.utils.exeptions.BusinessExeption;
import org.awwa.utils.logs.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author John
 *
 */
@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		List<GrantedAuthority> mappedAuthorities = new ArrayList<GrantedAuthority>();

		try {
			mappedAuthorities.addAll(this.usuarioRepository.getGrantedAuthority(arg0));
		} catch (BusinessExeption e) {
			Logs.getError().debug(e.getMessage(), e);
			e.printStackTrace();
		}

		return new User(arg0, "", true, true, true, true, mappedAuthorities);
	}

}
