/**
 * 
 */
package org.awaa.services.administracion.impl;

import org.awaa.repository.administracion.PerfilRepository;
import org.awaa.services.administracion.PerfilService;
import org.awaa.utils.beans.administracion.Perfil;
import org.awwa.utils.exeptions.BusinessExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author John
 *
 */
@Service("PerfilService")
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	public Perfil almacenarPerfil(Perfil perfil, String usuario) throws BusinessExeption {
		return this.perfilRepository.almacenarPerfil(perfil, usuario);
	}

	@Override
	public void removerPerfil(Perfil perfil) throws BusinessExeption {
		this.perfilRepository.removerPerfil(perfil);
	}
}
