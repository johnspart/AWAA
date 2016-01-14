/**
 * 
 */
package org.awaa.orm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;

/**
 * @author John
 *
 */
@Embeddable
public class Auditoria {
	@Column(name = "userCrea", nullable = false, insertable = true, updatable = false)
	private String userCrea;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaCrea", nullable = false, insertable = true, updatable = false)
	private Date fechaCrea;
	@Column(name = "userMod", nullable = true, insertable = false, updatable = true)
	private String userMod;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaMod", nullable = true, insertable = false, updatable = true)
	private Date fechaMod;

	public Auditoria() {
		super();
	}

	public Auditoria(String usuario) {
		super();
		this.fechaCrea = this.fechaMod = new Date();
		this.userCrea = this.userMod = usuario;
	}

	/**
	 * @return the userCrea
	 */
	public String getUserCrea() {
		return userCrea;
	}

	/**
	 * @param userCrea
	 *            the userCrea to set
	 */
	public void setUserCrea(String userCrea) {
		this.userCrea = userCrea;
	}

	/**
	 * @return the fechaCrea
	 */
	public Date getFechaCrea() {
		return fechaCrea;
	}

	/**
	 * @param fechaCrea
	 *            the fechaCrea to set
	 */
	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	/**
	 * @return the userMod
	 */
	public String getUserMod() {
		return userMod;
	}

	/**
	 * @param userMod
	 *            the userMod to set
	 */
	public void setUserMod(String userMod) {
		this.userMod = userMod;
	}

	/**
	 * @return the fechaMod
	 */
	public Date getFechaMod() {
		return fechaMod;
	}

	/**
	 * @param fechaMod
	 *            the fechaMod to set
	 */
	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}
}
