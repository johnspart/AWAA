/**
 * 
 */
package org.awaa.services.administracion.impl;

import java.util.List;

import org.awaa.repository.administracion.UsuarioRepository;
import org.awaa.services.administracion.UsuarioService;
import org.awaa.utils.beans.administracion.Usuario;
import org.awwa.utils.exeptions.BusinessExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author John
 *
 */
@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired(required = true)
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> getUsuarios() throws BusinessExeption{
		return this.usuarioRepository.getUsuarios();
	}

}
