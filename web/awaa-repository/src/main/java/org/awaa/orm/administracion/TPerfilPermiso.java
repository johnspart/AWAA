/**
 * 
 */
package org.awaa.orm.administracion;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.awaa.orm.Auditoria;
import org.awaa.utils.enums.administracion.Permiso;

/**
 * @author John
 *
 */
@Entity
@Table(name = "TPerfilPermiso", catalog = "C_GROUP")
public class TPerfilPermiso {
	@EmbeddedId
	private TPerfilPermisoId tPerfilPermisoId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pfpPerfil", insertable = false, updatable = false, referencedColumnName = "prfId", foreignKey = @ForeignKey(name = "FK_TPerfil_TPerfilPermiso_pfpPerfil") )
	private TPerfil tPerfil;
	@Enumerated(EnumType.STRING)
	@Column(name = "pfpPermiso", insertable = false, updatable = false)
	private Permiso pfpPermiso;
	@Embedded
	private Auditoria auditoria;

	public TPerfilPermisoId gettPerfilPermisoId() {
		return tPerfilPermisoId;
	}

	public void settPerfilPermisoId(TPerfilPermisoId tPerfilPermisoId) {
		this.tPerfilPermisoId = tPerfilPermisoId;
	}

	public TPerfil gettPerfil() {
		return tPerfil;
	}

	public void settPerfil(TPerfil tPerfil) {
		this.tPerfil = tPerfil;
	}

	public Permiso getPfpPermiso() {
		return pfpPermiso;
	}

	public void setPfpPermiso(Permiso pfpPermiso) {
		this.pfpPermiso = pfpPermiso;
	}

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}
}
