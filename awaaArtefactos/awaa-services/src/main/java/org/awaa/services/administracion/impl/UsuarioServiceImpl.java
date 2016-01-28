/**
 * 
 */
package org.awaa.services.administracion.impl;

import java.util.List;

import org.awaa.repository.administracion.UsuarioRepository;
import org.awaa.services.administracion.SendEmail;
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
	private SendEmail sendEmail;
	@Autowired(required = true)
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> getUsuarios() throws BusinessExeption {
		return this.usuarioRepository.getUsuarios();
	}

	@Override
	public void sendEmailRestartPass(String usuario) throws BusinessExeption {
		String email = this.usuarioRepository.getEmail(usuario);
		if (email == null)
			throw new BusinessExeption("noExisUser=".concat(usuario));

		this.sendEmail.enviar("Restaurar contraseña", this.usuarioRepository.updateUsrRestaurarContrasenna(usuario),
				email);

	}
}
