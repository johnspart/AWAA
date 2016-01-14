/**
 * 
 */
package org.awaa.utils.enums.administracion;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author John
 *
 */

public enum Permiso implements GrantedAuthority {
	// Administracion
	PERFILES, 
	USUARIOS;

	@Override
	public String getAuthority() {
		return this.name();
	}

	public static String[] getNames() {
		String[] names = new String[Permiso.values().length];
		for (Permiso enmTmp : Permiso.values()) {
			names[enmTmp.ordinal()] = enmTmp.name();
		}
		return names;
	}
}
