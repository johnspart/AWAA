/**
 * 
 */
package org.awaa.orm.administracion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author John
 *
 */
@Embeddable
public class TPerfilPermisoId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6583441563671973497L;

	@Column(name = "pfpPermiso", length = 120)
	private String pfpPermiso;
	@Column(name = "pfpPerfil")
	private Long pfpPerfil;

	public String getPfpPermiso() {
		return pfpPermiso;
	}

	public void setPfpPermiso(String pfpPermiso) {
		this.pfpPermiso = pfpPermiso;
	}

	public Long getPfpPerfil() {
		return pfpPerfil;
	}

	public void setPfpPerfil(Long pfpPerfil) {
		this.pfpPerfil = pfpPerfil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pfpPerfil == null) ? 0 : pfpPerfil.hashCode());
		result = prime * result + ((pfpPermiso == null) ? 0 : pfpPermiso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TPerfilPermisoId other = (TPerfilPermisoId) obj;
		if (pfpPerfil == null) {
			if (other.pfpPerfil != null)
				return false;
		} else if (!pfpPerfil.equals(other.pfpPerfil))
			return false;
		if (pfpPermiso == null) {
			if (other.pfpPermiso != null)
				return false;
		} else if (!pfpPermiso.equals(other.pfpPermiso))
			return false;
		return true;
	}

}
