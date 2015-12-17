/**
 * 
 */
package org.awaa.controller.administracion;

import java.util.List;

import org.awaa.services.administracion.PerfilService;
import org.awaa.utils.beans.administracion.Perfil;
import org.awwa.utils.logs.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author john
 *
 */
@RestController()
@RequestMapping("perfilController")
public class PerfilesController {
	@Autowired
	private PerfilService perfilService;

	@RequestMapping(value = "/getPerfiles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Perfil> getPerfiles() {
		try {
			return this.perfilService.getPerfiles();
		} catch (Exception e) {
			Logs.getError().error(e.getMessage(), e);
			e.printStackTrace();
			return null;
		}
	}
}
