/**
 * 
 */
package org.awaa.services.administracion;

import org.awaa.utils.beans.administracion.Perfil;
import org.awwa.utils.exeptions.BusinessExeption;

/**
 * @author John
 *
 */
public interface PerfilService {

	Perfil almacenarPerfil(Perfil perfil, String usuario) throws BusinessExeption;

	void removerPerfil(Perfil perfil) throws BusinessExeption;

}
