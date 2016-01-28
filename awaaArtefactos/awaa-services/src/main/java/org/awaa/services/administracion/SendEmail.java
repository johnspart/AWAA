/**
 * 
 */
package org.awaa.services.administracion;

import org.awwa.utils.exeptions.BusinessExeption;

/**
 * @author john.lopez
 *
 */
public interface SendEmail {

	void enviar(String asunto, String mensaje, String... destinatarios) throws BusinessExeption;

}
