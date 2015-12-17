/**
 * 
 */
package org.awaa.controller.administracion;

import java.util.List;

import org.awaa.utils.beans.administracion.Perfil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author john
 *
 */
@RestController
public class PerfilesController {

	@RequestMapping(value = "/getPerfiles", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Perfil> getPerfiles() {
		return null;
	}
}
