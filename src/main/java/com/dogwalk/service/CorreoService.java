package com.dogwalk.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.dogwalk.util.Constantes;

@Service
public class CorreoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JavaMailSender mailSender;

	@Autowired
	private CorreoService(JavaMailSender mailSender){
		this.mailSender = mailSender;
	}
	/*private JavaMailSender mailSender;*/


	public boolean enviarCorreo(String correo, String contrasenaAutoGenerada) {

		String nombreMetodo = "enviarCorreo";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_CORREO, nombreMetodo, Constantes.LOG_METODO_INICIO);

		boolean emailSent = false;
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage);

		String emailText = "Hola!"/* + '\n\n' +
		"Hemos reseteado tu contraseña de acceso a DogWalk." + 
		'\n\n' + "Tu nueva contraseña autogenerada es"*/ + contrasenaAutoGenerada /*
		+ '\n\n' +
		"Una vez que hayas entrado a la app, el sistema te pedirá que cambies la contraseña autogenerada." 
		+ '\n\n' +
		"Atentamente," +
		'\n\n' +
		 "El equipo de DogWalk."*/;

		try {

			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_CORREO, nombreMetodo,
					Constantes.ENVIO_CORREO_INICIO);

			// Destinatario
			mailMessage.setTo(correo);

			// Asunto
			mailMessage.setSubject(Constantes.ASUNTO_CORREO_RECUPERACION);

			// Texto del correo
			mailMessage.setText(emailText, true);

			mailSender.send(mimeMessage);

			emailSent = true;

			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_CORREO, nombreMetodo,
					Constantes.ENVIO_CORREO_FIN);

		} catch (MessagingException | MailException e) {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_CORREO, nombreMetodo, e.getMessage());
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_CORREO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return emailSent;
	}

}
