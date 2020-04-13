package com.dogwalk.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogwalk.dto.MensajeDto;
import com.dogwalk.dto.UsuarioDto;
import com.dogwalk.entity.UsuarioEntity;
import com.dogwalk.service.UsuarioService;
import com.dogwalk.util.Constantes;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UsuarioService usuarioService;

	@PostMapping("")
	public ResponseEntity<UsuarioDto> crearUsuario(@Valid @RequestBody UsuarioEntity usuarioEntity) {

		String nombreMetodo = "crearUsuario";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_USUARIO, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		UsuarioDto usuarioDto = usuarioService.crearUsuario(usuarioEntity);

		if (usuarioDto.getId() != null && usuarioDto.getId() > 0) {
			status = HttpStatus.OK;
		}

		ResponseEntity<UsuarioDto> responseEntity = new ResponseEntity<>(usuarioDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_USUARIO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@PostMapping("/login")
	public ResponseEntity<UsuarioDto> login(@RequestHeader("usuario") String usuario,
			@RequestHeader("contrasena") String contrasena) {

		String nombreMetodo = "login";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_USUARIO, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		UsuarioDto loginDto = usuarioService.login(usuario, contrasena);

		if (loginDto.getId() != null && loginDto.getId() > 0) {
			status = HttpStatus.OK;
		}

		ResponseEntity<UsuarioDto> responseEntity = new ResponseEntity<>(loginDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_USUARIO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@PutMapping("/cambiarContrasena")
	public ResponseEntity<MensajeDto> cambiarContrasena(@RequestHeader("idUsuario") Integer idUsuario,
			@RequestHeader("contrasenaActual") String contrasenaActual,
			@RequestHeader("contrasenaNueva") String contrasenaNueva) {

		String nombreMetodo = "cambiarContrasena";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_USUARIO, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		MensajeDto mensajeDto = usuarioService.cambiarContrasena(idUsuario, contrasenaActual, contrasenaNueva);

		if (mensajeDto.getMensaje().contains(Constantes.CAMBIO_CONTRASENA_EXITOSO)) {
			status = HttpStatus.OK;
		}

		ResponseEntity<MensajeDto> responseEntity = new ResponseEntity<>(mensajeDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_USUARIO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@GetMapping("/recuperarContrasena/{correo}")
	public ResponseEntity<MensajeDto> recuperarContrasena(@PathVariable("correo") String correo) {

		String nombreMetodo = "recuperarContrasena";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_USUARIO, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		MensajeDto mensajeDto = usuarioService.recuperarContrasena(correo);

		if (mensajeDto.getMensaje().contains(Constantes.ENVIO_CONTRASENA_EXITOSO)) {
			status = HttpStatus.OK;
		}

		ResponseEntity<MensajeDto> responseEntity = new ResponseEntity<>(mensajeDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_USUARIO, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

}
