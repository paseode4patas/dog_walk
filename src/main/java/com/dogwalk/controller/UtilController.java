package com.dogwalk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogwalk.service.UtilService;
import com.dogwalk.util.Constantes;

@RestController
@RequestMapping("/util")
public class UtilController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UtilService utilService;

	@GetMapping("/encriptarConBase64/{textoPorEncriptar}")
	public ResponseEntity<String> encriptarConBase64(@PathVariable("textoPorEncriptar") String textoPorEncriptar) {

		String nombreMetodo = "encriptarConBase64";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo, Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String textoEncriptado = utilService.encriptarConBase64(textoPorEncriptar);

		if (textoEncriptado != null) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<String> responseEntity = new ResponseEntity<>(textoEncriptado, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@GetMapping("/desencriptarConBase64/{textoPorDesencriptar}")
	public ResponseEntity<String> desencriptarConBase64(
			@PathVariable("textoPorDesencriptar") String textoPorDesencriptar) {

		String nombreMetodo = "desencriptarConBase64";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo, Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String textoDesencriptado = utilService.desencriptarConBase64(textoPorDesencriptar);

		if (textoDesencriptado != null) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<String> responseEntity = new ResponseEntity<>(textoDesencriptado, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@GetMapping("/generarContrasena")
	public ResponseEntity<String> generarContrasena() {

		String nombreMetodo = "generarContrasena";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo, Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String contrasenaAutogenerada = utilService.generarContrasena(Constantes.LONGITUD_CONTRASENA);

		if (contrasenaAutogenerada != null) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<String> responseEntity = new ResponseEntity<>(contrasenaAutogenerada, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_UTIL, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

}
