/**
 * 
 */
package org.awaa.test.data.administracion;

import java.util.ArrayList;
import java.util.List;

import org.awaa.utils.beans.administracion.Perfil;

/**
 * @author john
 *
 */
public class DatosPerfil {
	private static List<Perfil> perfils;

	public static List<Perfil> getPerfils() {
		perfils = new ArrayList<Perfil>();
		perfils.add(new Perfil(1L, "Prueba 1", "Prueba 1"));
		perfils.add(new Perfil(2L, "Prueba 2", "Prueba 2"));
		return perfils;
	}

}
