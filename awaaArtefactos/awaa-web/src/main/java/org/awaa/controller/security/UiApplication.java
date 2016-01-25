/**
 * 
 */
package org.awaa.controller.security;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

	@RequestMapping("/resource")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}

	@RequestMapping("/token")
	@ResponseBody
	public Map<String, String> token(HttpSession session) {
		return Collections.singletonMap("token", session.getId());
	}
}