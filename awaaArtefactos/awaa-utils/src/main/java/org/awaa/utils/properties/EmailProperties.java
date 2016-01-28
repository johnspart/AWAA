/**
 * 
 */
package org.awaa.utils.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author john.lopez
 *
 */
@Component
@PropertySource("classpath:org/awaa/utils/properties/email.properties")
public class EmailProperties {
	@Value("${userEmail}")
	private String userEmail;
	@Value("${passEmail}")
	private String passEmail;
	@Value("${serverEmail}")
	private String serverEmail;
	@Value("${portEmail}")
	private String portEmail;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassEmail() {
		return passEmail;
	}

	public void setPassEmail(String passEmail) {
		this.passEmail = passEmail;
	}

	public String getServerEmail() {
		return serverEmail;
	}

	public void setServerEmail(String serverEmail) {
		this.serverEmail = serverEmail;
	}

	public String getPortEmail() {
		return portEmail;
	}

	public void setPortEmail(String portEmail) {
		this.portEmail = portEmail;
	}
}
