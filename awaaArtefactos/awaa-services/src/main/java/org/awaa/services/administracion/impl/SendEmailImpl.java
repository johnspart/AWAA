/**
 * 
 */
package org.awaa.services.administracion.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.awaa.services.administracion.SendEmail;
import org.awaa.utils.properties.EmailProperties;
import org.awwa.utils.exeptions.BusinessExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author john.lopez
 *
 */
@Service
public class SendEmailImpl implements SendEmail {
	@Autowired
	private EmailProperties emailProperties;
	private Session session = null;

	@Override
	public void enviar(String asunto, String mensaje, String... destinatarios) throws BusinessExeption {
		if (destinatarios.length < 1)
			throw new BusinessExeption("La lista de destinatarios esta vacia ");
		this.loadProperties();
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(this.emailProperties.getUserEmail()));

			// Set To: header field of the header.
			for (String to : destinatarios)
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(asunto);

			// Now set the actual message
			message.setContent(mensaje, "text/html");

			// Send message
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new BusinessExeption(e);
		}
	}

	private void loadProperties() throws BusinessExeption {
		Properties props = new Properties();
		props.put("mail.smtp.host", emailProperties.getServerEmail());
		props.put("mail.smtp.socketFactory.port", emailProperties.getPortEmail());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.port", "26");

		this.session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailProperties.getUserEmail(), emailProperties.getPassEmail());
			}
		});
	}
}
