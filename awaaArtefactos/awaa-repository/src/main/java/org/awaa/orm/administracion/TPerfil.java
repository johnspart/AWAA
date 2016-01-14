/**
 * 
 */
package org.awaa.orm.administracion;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.awaa.orm.Auditoria;

/**
 * @author John
 *
 */
@Entity
@Table(name = "TPerfil", schema = "Administracion")
public class TPerfil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prfId")
	private Long prfId;
	@Column(name = "prfPerfil", length = 100)
	private String prfPerfil;
	@Column(name = "prfDescripcion", length = 250, nullable = true)
	private String prfDescripcion;
	@Embedded
	private Auditoria auditoria;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tPerfil", cascade = { CascadeType.REMOVE })
	private Set<TPerfilPermiso> tPerfilPermisos = new HashSet<TPerfilPermiso>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tPerfil")
	private Set<TUsuario> tUsuarios = new HashSet<TUsuario>(0);

	public Long getPrfId() {
		return prfId;
	}

	public void setPrfId(Long prfId) {
		this.prfId = prfId;
	}

	public String getPrfPerfil() {
		return prfPerfil;
	}

	public void setPrfPerfil(String prfPerfil) {
		this.prfPerfil = prfPerfil;
	}

	public String getPrfDescripcion() {
		return prfDescripcion;
	}

	public void setPrfDescripcion(String prfDescripcion) {
		this.prfDescripcion = prfDescripcion;
	}

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

	public Set<TPerfilPermiso> gettPerfilPermisos() {
		return tPerfilPermisos;
	}

	public void settPerfilPermisos(Set<TPerfilPermiso> tPerfilPermisos) {
		this.tPerfilPermisos = tPerfilPermisos;
	}

	public Set<TUsuario> gettUsuarios() {
		return tUsuarios;
	}

	public void settUsuarios(Set<TUsuario> tUsuarios) {
		this.tUsuarios = tUsuarios;
	}
}
