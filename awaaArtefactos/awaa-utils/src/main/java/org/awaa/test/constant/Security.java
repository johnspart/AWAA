/**
 * 
 */
package org.awaa.test.constant;

/**
 * @author john
 *
 */
public enum Security {
	USER_ACTION("MOCK");

	private String codigo;

	private Security(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return this.codigo;
	}
}
