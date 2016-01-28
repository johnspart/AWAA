/**
 * 
 */
package org.awaa.controller.administracion;

import java.util.List;

import org.awaa.services.administracion.PerfilService;
import org.awaa.utils.beans.administracion.Perfil;
import org.awaa.utils.enums.administracion.Permiso;
import org.awwa.utils.logs.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author john
 *
 */
@RestController
@RequestMapping("perfilController")
public class PerfilController {
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

	@RequestMapping(value = "/getPerfil/{idPerfil}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Permiso> getPerfil(
			@PathVariable("idPerfil") @NumberFormat(style = Style.NUMBER) Long idPerfil) {
		try {
			return this.perfilService.getPermisosPerfil(new Perfil(idPerfil));
		} catch (Exception e) {
			Logs.getError().error(e.getMessage(), e);
			e.printStackTrace();
			return null;
		}
	}
}
