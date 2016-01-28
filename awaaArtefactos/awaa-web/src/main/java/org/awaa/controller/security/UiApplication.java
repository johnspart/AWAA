/**
 * 
 */
package org.awaa.controller.security;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author john.lopez
 *
 */

@RestController
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class UiApplication {

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/token")
	@ResponseBody
	public Map<String, String> token(HttpSession session) {
		return Collections.singletonMap("token", session.getId());
	}
}