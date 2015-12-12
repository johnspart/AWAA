/**
 * 
 */
package org.awaa.repository.administracion.impl;

import org.awaa.dao.GenericDAOImpl;
import org.awaa.orm.Auditoria;
import org.awaa.orm.administracion.TPerfil;
import org.awaa.repository.administracion.PerfilRepository;
import org.awaa.utils.beans.administracion.Perfil;
import org.awwa.utils.exeptions.BusinessExeption;
import org.springframework.stereotype.Repository;

/**
 * @author John
 *
 */
@Repository
public class PerfilRepositoryImpl extends GenericDAOImpl<TPerfil, Long> implements PerfilRepository {

	@Override
	public Perfil almacenarPerfil(Perfil perfil, String usuario) throws BusinessExeption {
		TPerfil tPerfil = new TPerfil();
		tPerfil.setPrfId(perfil.getIdPerfil());
		tPerfil.setPrfDescripcion(perfil.getDescripcion());
		tPerfil.setAuditoria(new Auditoria(usuario));

		super.saveOrUpdate(tPerfil);

		perfil.setIdPerfil(tPerfil.getPrfId());
		return perfil;
	}

	@Override
	public void removerPerfil(Perfil perfil) throws BusinessExeption {
		TPerfil tPerfil = super.find(TPerfil.class, perfil.getIdPerfil());
		if (tPerfil != null)
			super.delete(tPerfil);
	}
}
