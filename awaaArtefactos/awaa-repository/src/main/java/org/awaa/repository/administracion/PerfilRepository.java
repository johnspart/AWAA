/**
 * 
 */
package org.awaa.repository.administracion;

import java.util.List;

import org.awaa.dao.GenericDAO;
import org.awaa.orm.administracion.TPerfil;
import org.awaa.utils.beans.administracion.Perfil;
import org.awaa.utils.enums.administracion.Permiso;
import org.awwa.utils.exeptions.BusinessExeption;

/**
 * @author John
 *
 */
public interface PerfilRepository extends GenericDAO<TPerfil, Long> {

	Perfil almacenarPerfil(Perfil perfil, String usuario) throws BusinessExeption;

	void removerPerfil(Perfil perfil) throws BusinessExeption;

	List<Perfil> getPerfiles() throws BusinessExeption;

	List<Permiso> getPermisosPerfil(Perfil perfil) throws BusinessExeption;

	Perfil getPerfilById(Long idPerfil) throws BusinessExeption;

}
