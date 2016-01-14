/**
 * 
 */
package org.awaa.services.administracion.impl;

import java.util.List;

import org.awaa.repository.administracion.PerfilRepository;
import org.awaa.services.administracion.PerfilService;
import org.awaa.utils.beans.administracion.Perfil;
import org.awaa.utils.enums.administracion.Permiso;
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

	@Override
	public List<Perfil> getPerfiles() throws BusinessExeption {
		return this.perfilRepository.getPerfiles();
	}

	@Override
	public List<Permiso> getPermisosPerfil(Perfil perfil) throws BusinessExeption {
		return this.perfilRepository.getPermisosPerfil(perfil);
	}
}
