/**
 * 
 */
package org.awaa.repository.administracion.impl;

import java.util.List;

import org.awaa.dao.GenericDAOImpl;
import org.awaa.orm.Auditoria;
import org.awaa.orm.administracion.TPerfil;
import org.awaa.orm.administracion.TPerfilPermiso;
import org.awaa.repository.administracion.PerfilRepository;
import org.awaa.utils.beans.administracion.Perfil;
import org.awaa.utils.enums.administracion.Permiso;
import org.awwa.utils.exeptions.BusinessExeption;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 * @author John
 *
 */
@Repository("PerfilRepository")
public class PerfilRepositoryImpl extends GenericDAOImpl<TPerfil, Long> implements PerfilRepository {

	@Override
	public Perfil almacenarPerfil(Perfil perfil, String usuario) throws BusinessExeption {
		TPerfil tPerfil = new TPerfil();
		tPerfil.setPrfId(perfil.getIdPerfil());
		tPerfil.setPrfDescripcion(perfil.getDescripcion());
		tPerfil.setAuditoria(new Auditoria(usuario));

		super.saveOrUpdate(TPerfil.class, tPerfil);

		perfil.setIdPerfil(tPerfil.getPrfId());
		return perfil;
	}

	@Override
	public void removerPerfil(Perfil perfil) throws BusinessExeption {
		TPerfil tPerfil = super.find(TPerfil.class, perfil.getIdPerfil());
		if (tPerfil != null)
			super.delete(TPerfil.class, tPerfil);
	}

	@Override
	public List<Perfil> getPerfiles() throws BusinessExeption {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(TPerfil.class, "PRF");

		ProjectionList prjLst = Projections.projectionList();
		prjLst.add(Projections.property("PRF.prfId"), "idPerfil");
		prjLst.add(Projections.property("PRF.prfPerfil"), "perfil");
		prjLst.add(Projections.property("PRF.prfDescripcion"), "descripcion");

		return super.findCriteriaDinamico(Perfil.class, dCriteria, Transformers.aliasToBean(Perfil.class), prjLst);
	}

	@Override
	public List<Permiso> getPermisosPerfil(Perfil perfil) throws BusinessExeption {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(TPerfilPermiso.class, "PFP");

		dCriteria.add(Restrictions.eq("PFP.tPerfilPermisoId.pfpPerfil", perfil.getIdPerfil()));

		return super.findCriteriaDinamico(Permiso.class, dCriteria, DetachedCriteria.PROJECTION,
				Projections.property("pfpPermiso"));
	}

	@Override
	public Perfil getPerfilById(Long idPerfil) throws BusinessExeption {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(TPerfil.class, "PRF");

		dCriteria.add(Restrictions.eq("PRF.prfId", idPerfil));

		ProjectionList prjLst = Projections.projectionList();
		prjLst.add(Projections.property("PRF.prfId"), "idPerfil");
		prjLst.add(Projections.property("PRF.prfPerfil"), "perfil");
		prjLst.add(Projections.property("PRF.prfDescripcion"), "descripcion");

		return super.findCriteriaDinamicouniqueResult(Perfil.class, dCriteria, Transformers.aliasToBean(Perfil.class),
				prjLst);
	}
}
