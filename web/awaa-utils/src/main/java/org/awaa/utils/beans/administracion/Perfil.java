package org.awaa.utils.beans.administracion;

import java.util.List;

import org.awaa.utils.enums.administracion.Permiso;

public class Perfil {
	private Long idPerfil;
	private String perfil;
	private String descripcion;
	private List<Permiso> permisos;

	public Perfil() {
		super();
	}

	public Perfil(Long idPerfil) {
		super();
		this.idPerfil = idPerfil;
	}

	/**
	 * @return the idPerfil
	 */
	public Long getIdPerfil() {
		return idPerfil;
	}

	/**
	 * @param idPerfil
	 *            the idPerfil to set
	 */
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}
}
