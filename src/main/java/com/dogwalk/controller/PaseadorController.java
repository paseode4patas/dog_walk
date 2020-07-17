package com.dogwalk.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogwalk.dto.PaseadorDto;
import com.dogwalk.entity.PaseadorEntity;
import com.dogwalk.service.PaseadorService;
import com.dogwalk.util.Constantes;

@RestController
@RequestMapping("/paseador")
public class PaseadorController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PaseadorService paseadorService;

	@PostMapping("")
	public ResponseEntity<PaseadorDto> crearPaseador(@Valid @RequestBody PaseadorEntity paseadorEntity) {

		String nombreMetodo = "crearPaseador";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_PASEADOR, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		PaseadorDto paseadorDto = paseadorService.crearPaseador(paseadorEntity);

		if (paseadorDto.getId() != null && paseadorDto.getId() > 0) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_PASEADOR, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_PASEADOR, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<PaseadorDto> responseEntity = new ResponseEntity<>(paseadorDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_PASEADOR, nombreMetodo,
				Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

	@GetMapping("")
	public ResponseEntity<List<PaseadorDto>> listarPaseador() {

		String nombreMetodo = "listarPaseador";
		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_PASEADOR, nombreMetodo,
				Constantes.LOG_METODO_INICIO);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<PaseadorDto> listaPaseadorDto = paseadorService.listarPaseador();

		if (listaPaseadorDto != null) {
			status = HttpStatus.OK;
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_EXITOSO);
		} else {
			logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo,
					Constantes.LOG_METODO_ESTATUS_FALLIDO);
		}

		ResponseEntity<List<PaseadorDto>> responseEntity = new ResponseEntity<>(listaPaseadorDto, status);

		logger.info(Constantes.LOG_FORMATO, Constantes.LOG_CONTROLLER_MASCOTA, nombreMetodo, Constantes.LOG_METODO_FIN);

		return responseEntity;
	}

}
