/**
 * 
 */
package org.awaa.repository.administracion.impl;

import java.util.List;

import org.awaa.dao.GenericDAOImpl;
import org.awaa.orm.administracion.TUsuario;
import org.awaa.orm.utils.AliasToBeanNestedResultTransformer;
import org.awaa.repository.administracion.UsuarioRepository;
import org.awaa.utils.beans.administracion.Usuario;
import org.awwa.utils.exeptions.BusinessExeption;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

/**
 * @author John
 *
 */
@Repository
public class UsuarioRepositoryImpl extends GenericDAOImpl<TUsuario, String> implements UsuarioRepository {
	@Override
	public List<SimpleGrantedAuthority> getGrantedAuthority(String username) throws BusinessExeption {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TUsuario.class, "USR");
		detachedCriteria.createAlias("USR.tPerfil", "PRF");
		detachedCriteria.createAlias("PRF.tPerfilPermisos", "PFP");

		detachedCriteria.add(Restrictions.eq("USR.usrUsuario", username).ignoreCase());

		return super.findCriteriaDinamico(SimpleGrantedAuthority.class, detachedCriteria,
				DetachedCriteria.DISTINCT_ROOT_ENTITY, Projections.property("PFP.pfpPermiso"));
	}

	@Override
	public String getNombreUsuario(String usuario) throws BusinessExeption {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> getUsuarios() throws BusinessExeption {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(TUsuario.class, "USR");
		dCriteria.createAlias("USR.tPerfil", "PRF");

		ProjectionList pjLst = Projections.projectionList();
		pjLst.add(Projections.property("USR.usrUsuario"), "usuario");
		pjLst.add(Projections.property("PRF.prfId"), "perfil.idPerfil");
		pjLst.add(Projections.property("PRF.prfPerfil"), "perfil.perfil");
		pjLst.add(Projections.property("USR.usrNombre"), "nombre");
		pjLst.add(Projections.property("USR.usrCorreoElectronico"), "correoElectronico");

		return super.findCriteriaDinamico(Usuario.class, dCriteria,
				new AliasToBeanNestedResultTransformer(Usuario.class), pjLst);
	}
}
