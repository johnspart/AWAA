/**
 * 
 */
package org.awaa.repository.administracion;

import java.util.List;

import org.awaa.utils.beans.administracion.Perfil;
import org.awwa.utils.exeptions.BusinessExeption;

/**
 * @author John
 *
 */
public interface PerfilRepository {

	Perfil almacenarPerfil(Perfil perfil, String usuario) throws BusinessExeption;

	void removerPerfil(Perfil perfil) throws BusinessExeption;

	List<Perfil> getPerfiles() throws BusinessExeption;

}
