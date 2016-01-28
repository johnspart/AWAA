/**
 * 
 */
package org.awaa.repository.administracion.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.awaa.dao.GenericDAOImpl;
import org.awaa.orm.administracion.TUsuario;
import org.awaa.orm.utils.AliasToBeanNestedResultTransformer;
import org.awaa.repository.administracion.UsuarioRepository;
import org.awaa.utils.RandomStringGenerator;
import org.awaa.utils.beans.administracion.Usuario;
import org.awwa.utils.exeptions.BusinessExeption;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

/**
 * @author John
 *
 */
@Repository
public class UsuarioRepositoryImpl extends GenericDAOImpl<TUsuario, String> implements UsuarioRepository {
	private final String updateUsrRestarCont = "UPDATE TUsuario SET usrRestaurarContrasenna = :usrRestaurarContrasenna WHERE usrUsuario = :usrUsuario";

	@Autowired(required = true)
	private RandomStringGenerator randomGenerator;

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
	public String getPassEncode(String user) throws BusinessExeption {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(TUsuario.class, "USR");
		dCriteria.add(Restrictions.eq("USR.usrUsuario", user));
		return super.findCriteriaDinamicouniqueResult(String.class, dCriteria, DetachedCriteria.PROJECTION,
				Projections.property("USR.usrContrasenna"));
	}

	@Override
	public String getNombreUsuario(String usuario) throws BusinessExeption {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmail(String usuario) throws BusinessExeption {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(TUsuario.class, "USR");
		dCriteria.add(Restrictions.eq("USR.usrUsuario", usuario));
		return super.findCriteriaDinamicouniqueResult(String.class, dCriteria, DetachedCriteria.PROJECTION,
				Projections.property("USR.usrCorreoElectronico"));
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

	@Override
	public String updateUsrRestaurarContrasenna(String user) throws BusinessExeption {
		String random = this.randomGenerator.getRandomStringAppendTime();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usrRestaurarContrasenna", random);
		params.put("usrUsuario", user);
		super.editHQL(this.updateUsrRestarCont, params);
		return random;
	}
}
