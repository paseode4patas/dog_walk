package com.dogwalk.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dogwalk.util.Constantes;

@Service
public class UtilService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String encriptarConBase64(String textoPorEncriptar) {

		String nombreMetodo = "encriptarConBase64";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_UTIL, nombreMetodo, Constantes.LOG_METODO_INICIO);

		String textoEncriptado = "";
		textoEncriptado = Base64.getEncoder().encodeToString(textoPorEncriptar.getBytes());

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_UTIL, nombreMetodo, Constantes.LOG_METODO_FIN);

		return textoEncriptado;

	}

	public String desencriptarConBase64(String textoPorDesencriptar) {

		String nombreMetodo = "desencriptarConBase64";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_UTIL, nombreMetodo, Constantes.LOG_METODO_INICIO);

		byte[] bytesDesencriptado;
		String textoDesencriptado = "";

		bytesDesencriptado = Base64.getDecoder().decode(textoPorDesencriptar);
		textoDesencriptado = new String(bytesDesencriptado);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_UTIL, nombreMetodo, Constantes.LOG_METODO_FIN);

		return textoDesencriptado;

	}

	public String generarContrasena(int longitudContrasena) {

		String nombreMetodo = "generarContrasena";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_UTIL, nombreMetodo, Constantes.LOG_METODO_INICIO);

		String minuscula = "abcdefghijklmnñopqrstuvwxyz";
		String mayuscula = minuscula.toUpperCase();
		String numero = "0123456789";
		String simbolo = "*?#$%&()=+<>";

		String contrasenaTemporal;

		StringBuilder contrasena = new StringBuilder(longitudContrasena);

		// 1 mayúscula
		contrasena.append(obtenerCaracteres(mayuscula, 1));

		// 3 minúsculas
		contrasena.append(obtenerCaracteres(minuscula, 3));

		// 2 números
		contrasena.append(obtenerCaracteres(numero, 2));

		// Símbolo
		contrasena.append(obtenerCaracteres(simbolo, 0));

		contrasenaTemporal = new String(contrasena);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_UTIL, nombreMetodo, Constantes.LOG_METODO_FIN);

		return contrasenaTemporal;
	}

	private StringBuilder obtenerCaracteres(String texto, int numeroDeCaracteres) {

		String nombreMetodo = "obtenerCaracteres";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_UTIL, nombreMetodo, Constantes.LOG_METODO_INICIO);

		Random position = new SecureRandom();
		StringBuilder character = new StringBuilder();

		for (int i = 0; i < numeroDeCaracteres; i++) {
			character.append(texto.charAt(position.nextInt(texto.length())));
		}

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_SERVICE_UTIL, nombreMetodo, Constantes.LOG_METODO_FIN);

		return character;
	}

}
