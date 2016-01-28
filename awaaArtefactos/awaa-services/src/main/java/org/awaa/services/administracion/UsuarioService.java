/**
 * 
 */
package org.awaa.services.administracion;

import java.util.List;

import org.awaa.utils.beans.administracion.Usuario;
import org.awwa.utils.exeptions.BusinessExeption;

/**
 * @author John
 *
 */
public interface UsuarioService {

	List<Usuario> getUsuarios() throws BusinessExeption;

	void sendEmailRestartPass(String usuario) throws BusinessExeption;

}
