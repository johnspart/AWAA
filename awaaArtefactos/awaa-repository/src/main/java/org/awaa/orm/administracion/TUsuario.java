/**
 * 
 */
package org.awaa.orm.administracion;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.awaa.orm.Auditoria;

/**
 * @author John
 *
 */
@Entity
@Table(name = "TUsuario", schema = "Administracion")
public class TUsuario {
	@Id
	@Column(name = "usrUsuario", length = 200)
	private String usrUsuario;
	@Column(name = "usrContrasenna", nullable = false)
	private String usrContrasenna;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usrPerfil", nullable = false, referencedColumnName = "prfId", foreignKey = @ForeignKey(name = "FK_TPerfil_TUsuarios_prfPerfil") )
	private TPerfil tPerfil;
	@Column(name = "usrNombre", nullable = false, length = 250)
	private String usrNombre;
	@Column(name = "usrCorreoElectronico", nullable = false, length = 200)
	private String usrCorreoElectronico;
	@Column(name = "usrRestaurarContrasenna", nullable = true, length = 100)
	private String usrRestaurarContrasenna;
	@Embedded
	private Auditoria auditoria;

	public String getUsrUsuario() {
		return usrUsuario;
	}

	public void setUsrUsuario(String usrUsuario) {
		this.usrUsuario = usrUsuario;
	}

	public String getUsrContrasenna() {
		return usrContrasenna;
	}

	public void setUsrContrasenna(String usrContrasenna) {
		this.usrContrasenna = usrContrasenna;
	}

	public TPerfil gettPerfil() {
		return tPerfil;
	}

	public void settPerfil(TPerfil tPerfil) {
		this.tPerfil = tPerfil;
	}

	public String getUsrNombre() {
		return usrNombre;
	}

	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}

	public String getUsrCorreoElectronico() {
		return usrCorreoElectronico;
	}

	public void setUsrCorreoElectronico(String usrCorreoElectronico) {
		this.usrCorreoElectronico = usrCorreoElectronico;
	}

	public String getUsrRestaurarContrasenna() {
		return usrRestaurarContrasenna;
	}

	public void setUsrRestaurarContrasenna(String usrRestaurarContrasenna) {
		this.usrRestaurarContrasenna = usrRestaurarContrasenna;
	}

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}
}
