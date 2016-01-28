/**
 * 
 */
package org.awaa.controller.security;

import org.awaa.services.administracion.UsuarioService;
import org.awwa.utils.exeptions.BusinessExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author john.lopez
 *
 */
@RestController
public class ForgotController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(path = "/forgot", name = "/forgot", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
	public String restaurarContrasenna(@RequestParam(name = "userName") String user) throws BusinessExeption {
		this.usuarioService.sendEmailRestartPass(user);
		return null;
	}
}
