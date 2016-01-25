/**
 * 
 */
package org.awaa.repository.administracion;

import java.util.List;

import org.awaa.utils.beans.administracion.Usuario;
import org.awwa.utils.exeptions.BusinessExeption;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author John
 *
 */
public interface UsuarioRepository {

	String getNombreUsuario(String usuario) throws BusinessExeption;

	List<SimpleGrantedAuthority> getGrantedAuthority(String username) throws BusinessExeption;

	List<Usuario> getUsuarios() throws BusinessExeption;

	String getPassEncode(String user) throws BusinessExeption;

}
